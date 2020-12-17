(ns advent.day15
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


;; starting numbers
(def example [0,3,6])
(def input [0,3,1,6,7,5])


;; says 0, if the this is the 


;; Speak numbers from input
;; Then, consider the last number spoken
;;   If, it was the first time spoken then speak 0
;;   else, return the age (difference from when spoken and when spoken before)


;; 0 3 6 0 


;; * 0 3 6
;; - 0
;; - 3
;; - 6
;;   - 0
;;     - 3
;;     - 3
;;     - 1
;;       - 0
;;       - 4
;;         - 0

(defn find-pos [v lst]
  (loop [l lst
         pos 0]
    (if (= v (first l))
      (inc pos)
      (recur (next l) (inc pos)))))



(defn get-next [lst]
  (let [l (reverse lst)
        h (first l)
        t (rest l)]
    ;; (pp/pprint (format "%s %s" h t))
    ;; if h is new then add 0
    (if (nil? ((into #{} t) h))
      (conj lst 0)

      ;; add difference between last two instances
      (conj lst (find-pos h t)))))



(defn part1 [input pos]
  (let [length (count input)
        goal (- pos length)]
  (loop [l input
         cnt 0]
    (if ( = cnt goal)
      (last l)
      (recur (get-next l) (inc cnt))))))
