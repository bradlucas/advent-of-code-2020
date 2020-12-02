(ns advent.day01
  (:require [advent.core :as c]
            [clojure.math.combinatorics :as combo]))


(defn read-integers [file]
  (->> file
       (c/read-lines)
       (map #(Integer/parseInt %))))





(defn part1 [file]
  (let [ints (read-integers file)
        combinations (combo/permuted-combinations ints 2)]
    (apply * (first (filter (fn [[a b]] (= 2020 (+ a b))) combinations )))))





(defn part2 [file]
  (let [ints (read-integers file)
        combinations (combo/permuted-combinations ints 3)]
    (apply * (first (filter (fn [[a b c]] (= 2020 (+ a b c))) combinations )))))
