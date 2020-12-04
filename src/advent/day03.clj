(ns advent.day03
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


(defn read-input [file]
  (->> file
       (c/read-lines)
       (map seq)
       (map cycle)))

(def right 3)
(def down 1)

(defn step [{:keys [col row] :as pos}]
  (let [next {:col (+ col right)
              :row (+ row down)}]
    next))

(defn get [g {:keys [col row] :as pos}]
  (nth (nth g row) col))

(defn part1 [file]
  (let [grid (read-input file)
        rows (dec (count grid))]
    (loop [pos {:col 0 :row 0}
           path []]
      (let [row (:row pos)]
        (if (> row rows)
          (count (filter #(= \# %) path))
          (recur (step pos) (conj path (get grid pos))))))))
