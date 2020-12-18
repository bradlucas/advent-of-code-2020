(ns advent.day15-1
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


;; starting numbers
(def example '(0,3,6))   ;; => 436 
(def input '(0,3,1,6,7,5)) ;; => 852

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


;; Keep count of visited counts for each node
(defn get-next [{:keys [lst count]}]
  (let [[head tail] ((fn [l] [(first l)
                              (rest l)]) lst)]
    (if (= 1 (get count head 0))
      {:lst (conj lst 0)
       :count (assoc count 0 (inc (get count 0 0)))}
      (let [v (find-pos head tail)]
        {:lst (conj lst v)
         :count (assoc count v (inc (get count v 0)))}))))



(defn part1 [input pos]
  (let [lst (reverse input)]
    (loop [l {:lst lst
              :count (into {} (map (fn [v] {v 1})) lst)}
           cnt (count lst)]
      (if (= cnt pos)
        (first (:lst l))
        (recur (get-next l) (inc cnt))))))




(comment

  ;; Previous version
  advent.day15-1> (time (advent.day15/part1 input 2020))
  "Elapsed time: 273.825785 msecs"
  852

  ;; This version
  advent.day15-1> (time (advent.day15-1/part1 input 2020))
  "Elapsed time: 17.944404 msecs"
  852

  ;; Still doesn't return
  advent.day15-1> (time (advent.day15/part1 input 30000000))
    
  
  )
