(ns advent.day10
  (:require [advent.core :as c]
            [clojure.pprint :as pp]))

(defn read-input [file]
  (let [l (c/read-numbers file)]
    (sort (conj l 0 (+ 3 (apply max l))))))


(defn part1 [file]
  (let [lst (read-input file)]
    (apply * (vals (frequencies (map #(- %2 %1) lst (rest lst)))))))
