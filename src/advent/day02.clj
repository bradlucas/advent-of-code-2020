(ns advent.day02
  (:require [advent.core :as c]
            [clojure.math.combinatorics :as combo]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


(defn read-input [file]
  (->> file
       (c/read-lines)))

(defn parse-input [input]
  (let [[v c s] (str/split input #" ")
        chr (first (first (str/split c #":")))
        [c1 c2]  (map #(Integer/parseInt %) (str/split v #"-"))
        freq (frequencies s)
        cnt (get freq chr)]
    (when cnt
      (and  (>= cnt c1) (<= cnt c2)))))


(defn part1 [file]
  (let [lst (read-input file)]
    (count (filter true? (map parse-input lst)))))
