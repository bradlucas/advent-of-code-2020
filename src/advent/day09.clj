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



;; ----------------------------------------------------------------------------------------------------
;; part2
;; find contiguous set of at least two numbers
;; which has members add up to the number found in part 1
;; to find the encryption weakness add the smallest and largests number in the range


(defn sum-range [r]
  (apply + r))

(defn get-range [lst num]
  ;; starting at the head of lst, return the largest set that adds up to num
  ;; need at least two numbers
  ;; if not possible return nill
  (let [head (first lst)
        lst (rest lst)]
    (loop [lst lst
           acc [head]]
      (let [a (sum-range acc)
            b (first  lst)
            s (+ a b)]
        (if (= s num)
          (conj acc b)
          (if (> s num)
            nil
            (recur (rest lst) (conj acc b))))))))

(defn weakness [lst]
  (+ (first lst)
     (last lst)))

(defn process-ranges [lst num]
  (loop [lst lst]
    (let [r (get-range lst num)]
      (if r
        (weakness (sort r))
        (recur (rest lst))))))


(defn part2 [file num]
  (process-ranges (read-input file) num))
