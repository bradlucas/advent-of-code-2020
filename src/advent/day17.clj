(ns advent.day17
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))

;; ----------------------------------------------------------------------------------------------------
;; Looked at the following:
;; https://github.com/euccastro/advent-of-code-2020/blob/master/src/advent/day17.clj
;; https://github.com/zelark/AoC-2020/blob/master/src/zelark/aoc_2020/day_17.clj


(def example ".#.
..#
###")


;; euccastor
(defn initial-state [input]
  (into #{}
        (for [[y line] (map-indexed vector (str/split-lines input))
              [x char] (map-indexed vector line)
              :when (= char \#)]
          [x y 0])))

;; zelark
(defn parse-input [input n]
  (->> (str/split-lines input)
       (mapcat (fn [y line] (keep-indexed (fn [x ch] (when (= ch \#) [x y])) line)) (range))
       ;; [1 0] => [0]
       ;; [1 0] => [0 0]
       (map #(into % (repeat n 0)))
       set))


;; (for [[y line] (map-indexed vector (str/split-lines input))
;;       [x char] (map-indexed vector line)
;;       :when (= char \#)]

;; (mapcat (fn [y line]
;;           (keep-indexed (fn [x ch]
;;                           (when (= ch \#)
;;                             [x y])) line)) (range) (str/split-lines example))

;; ----------------------------------------------------------------------------------------------------

(defn read-input [file n]
  (-> (c/read-input file)
      ;; (initial-state)
      (parse-input n)
      ))

;; (range -1 2)
;; (-1 0 1)
(defn neighbors [[x y z]]
  (for [dx (range -1 2)
        dy (range -1 2)
        dz (range -1 2)
        :when (not= 0 dx dy dz)]
    [(+ x dx)
     (+ y dy)
     (+ z dz)]))

(defn step [neighbors cells]
  (set (for [[location num] (frequencies (mapcat neighbors cells))
             :when (or (= num 3) (and (= num 2) (cells location)))]
         location)))


(defn part1 [file]
  (->> (read-input file 1)
       (iterate (partial step neighbors))
       (drop 6)
       (first)
       (count)
       ))




;; advent.day17> (part1 "day17-example.txt")
;; 112
;; advent.day17> (part1 "day17.txt")
;; 338



(defn neighbors-4d [[x y z w]]
  (for [dx (range -1 2)
        dy (range -1 2)
        dz (range -1 2)
        dw (range -1 2)
        :when (not= 0 dx dy dz dw)]
    [(+ x dx)
     (+ y dy)
     (+ z dz)
     (+ w dw)]))


(defn part2 [file]
  (->> (read-input file 2)
       (iterate (partial step neighbors-4d))
       (drop 6)
       (first)
       (count)
       ))




;; advent.day17> (part2 "day17-example.txt")
;; 848
;; advent.day17> (part2 "day17.txt")
;; 2440
