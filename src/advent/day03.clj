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



;; Part2
;; Run for a number of slopes and multiple all the results

(def slopes [{:right 1 :down 1}
             {:right 3 :down 1}
             {:right 5 :down 1}
             {:right 7 :down 1}
             {:right 1 :down 2}])

(defn step2 [{:keys [right down] :as slope} {:keys [col row] :as pos}]
  (let [next {:col (+ col (:right slope))
              :row (+ row (:down slope))}]
    next))

(defn traverse [grid slope]
  (let [rows (dec (count grid))]
    (loop [pos {:col 0 :row 0}
           path []]
      (let [row (:row pos)]
        (if (> row rows)
          (count (filter #(= \# %) path))
          (recur (step2 slope pos) (conj path (get grid pos))))))))


(defn part2 [file]
  (let [grid (read-input file)]
    (apply * (map #(traverse grid %) slopes))))
