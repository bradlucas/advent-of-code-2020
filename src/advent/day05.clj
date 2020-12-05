(ns advent.day05
  (:require [advent.core :as c]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [clojure.string :as str]))

;; Total number of rows is 128

(def rows 128)
(def cols 8)

;; First 7 characters are either F or B
;; The last three characters will be either L or R


;; split-at 7
(defn read-input [file]
  (->> file
       (c/read-lines)
       (map #(split-at 7 %))))

;; FBFBBFFRLR
;; 44
;; (range 128)
;; \F  -- left-half
;; \B -- right-half

(defn lower-half [col]
  ;; split in the middle and return the left half
  (subvec col 0 (/ (count col) 2)))

(defn upper-half [col]
  ;; split in the middle and return the right half
  (subvec col (/ (count col) 2) (count col)))


(defn half [col c]
  (if (or (= \F c) (= \L c))
    (lower-half col)
    (upper-half col)))

(defn row [s]
  (first (reduce half (vec (range rows)) s)))

(defn col [s]
  (first (reduce half (vec (range cols)) s)))

(defn seat [row col]
  (+ (* row 8) col))

(defn part1 [file]
  (->> (read-input file)
       (map (fn [[a b]] (seat (row a) (col b))))
       (apply max)))



;; ----------------------------------------------------------------------------------------------------

;; Some of the seats are missing from the front and the back
;; Your seat should be the only missing boarding pass in your list

(defn not-diff-by-1 [[a b]]
  (not (= 1 (- b a))))

(defn part2 [file]
  (->> file
       read-input
       (map (fn [[a b]] (seat (row a) (col b))))
       sort
       (partition 2 1)
       (filter not-diff-by-1)
       first
       first
       inc))
