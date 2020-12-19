(ns advent.day16
  (:require [advent.core :as c]
            [clojure.set :as s]
            [clojure.pprint :as pp]
            [clojure.java.io :as io]
            [clojure.string :as str]))





;; ----------------------------------------------------------------------------------------------------

(defn read-lines [day]
  (with-open [rdr (io/reader (io/resource day))]
    (doall (line-seq rdr))))

(defn group-input [lst]
  ;; (("class: 1-3 or 5-7" "row: 6-11 or 33-44" "seat: 13-40 or 45-50")
  ;;  ("your ticket:" "7,1,14")
  ;;  ("nearby tickets:" "7,3,47" "40,4,50" "55,2,20" "38,6,12"))
  (filter #(not (= '("") %)) (partition-by #(= "" %) lst)))

(defn parse-num-pairs [s]
  (mapv #(Integer/parseInt %) (str/split s #"-")))

(defn build-ranges [d]
  (flatten (map (fn [[a b]] (range a (inc b))) d)))

(defn parse-fields [s]
  (let [[n d] (str/split s #":")
        d (-> d
              str/trim
              (str/split #" or "))
        data (map parse-num-pairs d)
        r (build-ranges data)]
    {:name (keyword n)
     :data r}))

(defn parse-number-list [s]
  (map #(Integer/parseInt %) (str/split s #",")))

(defn parse-ticket [s]
  (let [s (second s)]
    (parse-number-list s)))

(defn parse-nearby [n]
  (let [n (rest n)]
    (flatten (map parse-number-list n))))


(defn part1 [file]
  (let [[f t n] (group-input (read-lines file))
        fields (map parse-fields f)
        field-set (into #{} (flatten (map :data fields)))
        ticket (parse-ticket t)
        nearby (parse-nearby n)]
      (apply + (filter #(= nil (field-set %)) nearby))))
