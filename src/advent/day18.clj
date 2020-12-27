(ns advent.day18
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


(def example "1 + 2 * 3 + 4 * 5 + 6")

(def example2 "1 + (2 * 3) + (4 * (5 + 6))")

(def operators #{\+ \- \* \/})

(defn operator? [c]
  (operators c))

(defn operator-function [opr]
  (case opr
    \+ +
    \- -
    \* *
    \/ /))

(defn parse [s]
  (map #(char (first %)) (str/split s #" ")))

(defn process [l]
  (loop [l l
         previous nil
         opr nil]
    (pp/pprint l)
    (if (empty? l)
      previous

      ;; get next character
      ;; if operator then save to operator
      ;; else value
      ;;    if present previous and operator apply to value and save in previous
      ;;

      (let [c (first l)]
        (pp/pprint (format "%s %s %s" c previous opr))
        (pp/pprint (format "Operator? %s" (operator? c)))
        (pp/pprint (format "(and previous opr) => %s" (and previous opr)))
        (pp/pprint "----------------------------------------------------------------------------------------------------")
        (if (operator? c)
          (recur (rest l) previous c)
          (if (and previous opr)
            (do
              (pp/pprint (type previous))
              (pp/pprint (type c))
              (recur (rest l) ((operator-function opr) (Integer/parseInt (str previous)) (Integer/parseInt (str c))) nil))
            (recur (rest l) c nil)))))))

;; first char -> value
;; opaerator -> operator
;; second character
;;

(comment
  (process (parse example))   ;; => 71
  )




(defn part1 [file]

  )
