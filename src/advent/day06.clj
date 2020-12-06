(ns advent.day06
  (:require [advent.core :as c]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [clojure.string :as str]))



;; Each group is separated by a blank line
;; Each person's answers are on a single line
;; The answers are letters to identify the questions they answered yes to [a-z]

(defn read-input
  "Read input into groups by blank lines"
  [file]
    (-> file
      (io/resource)
      slurp
      (str/split #"\n\n")))


(defn part1
  "For each group remove blank lines and then count unique answers"
  [file]
  (->> file
      read-input
      (map #(into #{} (str/replace % #"\n" "")))
      (map count)
      (apply +)))
