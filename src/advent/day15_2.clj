(ns advent.day15-2
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


;; starting numbers
(def example [0,3,6])   ;; => 436 
(def input [0,3,1,6,7,5]) ;; => 852


;; @see https://github.com/zelark/AoC-2020/blob/master/src/zelark/aoc_2020/day_15.clj
(defn part1 [input pos]
  (loop [m (zipmap input (rest (range)))   ;; track positions
         cnt (count input)
         current (peek input)]
    (if (= cnt pos)
      current
      (if-let [last-num (m current)]
        (recur (assoc m current cnt) (inc cnt) (- cnt last-num))
        (recur (assoc m current cnt) (inc cnt) 0)))))



(defn part2 [input pos]
  (part1 input pos))



(comment

  advent.day15-2> (time (part1 input 2020))
  "Elapsed time: 1.567561 msecs"
  852
  
  advent.day15-2> (time (part1 input 30000000))
  "Elapsed time: 38002.320747 msecs"
  6007666

  )
