(ns advent.day15
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


;; starting numbers
(def example '(0,3,6))
(def input '(0,3,1,6,7,5))


(defn find-pos [v lst]
  ;; (pp/pprint (format "find-pos %s %s" v lst))
  (loop [l lst
         pos 0]
    (if (empty? l)
      (throw (Exception. (format "find-pos called with an unfindable value: %s %s" v lst )))
      )
    (if (= v (first l))
      (inc pos)
      (recur (next l) (inc pos)))))

(defn get-next [l]
  (let [h (first l)
        t (rest l)]
    ;; (pp/pprint (format "%s %s" h t))
    ;; if h is new then add 0
    (if (nil? ((into #{} t) h))
      (conj l 0)
      ;; add difference between last two instances
      (conj l (find-pos h t)))))

(defn part1 [input pos]
  (let [length (count input)
        goal (- pos length)]
  (loop [l (reverse input)
         cnt 0]
    (if ( = cnt goal)
      (first l)
      (recur (get-next l) (inc cnt))))))

