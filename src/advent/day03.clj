(ns advent.day03
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))



;; ..##.......
;; #...#...#..
;; .#....#..#.
;; ..#.#...#.#
;; .#...##..#.
;; ..#.##.....
;; .#.#.#....#
;; .#........#
;; #.##...#...
;; #...##....#
;; .#..#...#.#


(defn read-input [file]
  (->> file
       (c/read-lines)
       (map seq)
       (map cycle)))




;; line1 ...
;; line2 ..
;; line4 ...


;; counting all the trees you would encounter for the slope right 3, down 1

;; pos0 [0 0]
;; pos1 [3 1]
;; pos2 [6 2]

;; (take 10 (cycle [1 2]))

;; steps


(def right 3)
(def down 1)

(defn step [{:keys [col row] :as pos}]
  (let [next {:col (+ col right)
              :row (+ row down)}]
    next
    ))


;; (take 100 (iterate step {:col 0 :row 0}))

(defn get [g {:keys [col row] :as pos}]
  (nth (nth g row) col))



;; (count (filter #(= \# %) (part1 "day03.txt")))

(defn part1 [file]
  (let [grid (read-input file)
        rows (dec (count grid))]
    (loop [pos {:col 0 :row 0}
           path []]
      ;; (pp/pprint pos)
      (let [row (:row pos)]
        (if (> row rows)
          (count (filter #(= \# %) path))
          (recur (step pos) (conj path (get grid pos))))))))
