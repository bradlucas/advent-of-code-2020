(ns advent.day10
  (:require [advent.core :as c]
            [clojure.pprint :as pp]))

(defn read-input [file]
  (let [l (c/read-numbers file)]
    (sort (conj l 0 (+ 3 (apply max l))))))


(defn part1 [file]
  (let [lst (read-input file)]
    (apply * (vals (frequencies (map #(- %2 %1) lst (rest lst)))))))



;; Part2
;; Number of paths where the difference isn't more than 3
;; You can drop adapters if needed

;; @see https://github.com/benfle/advent-of-code-2020/blob/main/day10.clj
;; reverse the input
;; working from the beginning of the list (the tail)
;; for each node calc the number of paths possible to the next node
;; by looking for the existance of values idx + 1, idx + 2, idx +3
;; return the number found for the last node (the original first node

(defn count-next [acc n]
  (assoc acc n (+ (get acc (+ n 1) 0)
                  (get acc (+ n 2) 0)
                  (get acc (+ n 3) 0))))

(defn part2 [file]
  (let [lst (sort (read-input file))]
    (loop [acc {(last lst) 1}
           lst (rest (reverse lst))]
      (if (empty? lst)
        (acc 0)
        (recur (count-next acc (first lst)) (rest lst))))))


