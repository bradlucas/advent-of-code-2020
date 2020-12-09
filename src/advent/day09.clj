(ns advent.day09
  (:require [advent.core :as c]
            [clojure.math.combinatorics :as combo]
            [clojure.pprint :as pp]))


;; window is the number o numbers before the current value
;; value is valid if there is a pair of numbers in the window that add up to the number
;; find the first invalid number

(defn read-input [file]
  (vec (c/read-numbers file)))

(defn find-bad-value [lst size]
  (loop [lst lst]
    (let [get-num-window (fn [lst]
                           (let [ nums (take (inc size) lst)
                                 next (first (drop size nums))
                                 window (drop-last nums)]
                             [window next]))
          [window next] (get-num-window lst)
          sums (into #{} (map (fn [[a b]] (+ a b)) (combo/permuted-combinations window 2)))]
      (if (not (sums next))
        next
        (recur (rest lst))))))


(defn part1 [file size]
  (find-bad-value (read-input file) size))
