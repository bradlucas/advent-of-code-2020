(ns advent.day08
  (:require [advent.core :as c]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [clojure.set :as set]
            [clojure.string :as str]))


(defn parse-instruction [[i v]]
  [(keyword i)
   (Integer/parseInt v)])

(defn read-input [file]
  (->> file
      (c/read-lines)
      (map #(str/split % #" "))
      (map parse-instruction)))

(defn visited? [lst val]
  (some #{val} lst))

(defn move-next [program idx acc]
  (let [[instruction val] (nth program idx)]
    (case instruction
      :nop [(inc idx) acc]
      :jmp [(+ idx val) acc]
      :acc [(inc idx) (+ acc val)])))

(defn run [program]
  (loop [[idx acc] [0 0]
         visited []]
    (if (visited? visited idx)
      acc
      (recur (move-next program idx acc) (conj visited idx)))))

(defn part1 [file]
  (run (read-input file)))
