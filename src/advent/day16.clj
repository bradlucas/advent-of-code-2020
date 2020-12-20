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
    {:name (keyword (keyword (str/replace n " " "-")))
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



;; ----------------------------------------------------------------------------------------------------
;; remove rows with invalid information
;; figure out which position goes with which field
;; value the values in the fields which are named starting with "departure"

(defn parse-nearby2 [n]
  (let [n (rest n)]
    (map parse-number-list n)))

(defn valid-row [fs row]
  ;; return true if all values in row are in the field-set
  (let [cnt (count row)]
    (= cnt (count (filter #(not (nil? %)) (map fs row))))))

(defn find-seat-names [fields lst]
  ;; for all the numbers in l
  ;; return the names of the seats
  (loop [lst lst
         acc []]
    (if (empty? lst)
      acc
      (let [num (first lst)]
        (recur (rest lst) (conj acc (map (fn [[k v]]
                                           (if (= (v num) num) k)) fields)))))))
(defn decode-fields [fields pos acc s]
  (assoc acc pos (find-seat-names fields s)))

(defn decode-tickets [fields tickets]
  (loop [t tickets
         pos 1
         acc {}]
    (if (every? empty? t)
      acc
      (let [s (map first t)]
        (recur (map rest t) (inc pos) (decode-fields fields pos acc s))))))

(defn find-frequencies [r]
  (map (fn [[k v]]
         {k
          (frequencies (filter #(not (nil? %)) (flatten v)))})
       r))

;; (defn org-fields [l]
;;   (loop [l l]
;;     (if (empty? l)
;;       acc
;;       )))


(defn part2 [file]
  (let [[f t n] (group-input (read-lines file))
        fields (map parse-fields f)
        field-set (into #{} (flatten (map :data fields)))
        ticket (parse-ticket t)
        nearby (parse-nearby2 n)]
    (let [valid (filter #(valid-row field-set %) nearby)
          fields (into {} (map (fn [m] {(:name m) (into #{} (:data m))}) fields))
          tickets (conj nearby ticket)]
        ;; for all tickets find the field names what contain the ticket value at each position
      (find-frequencies (decode-tickets fields tickets)))))



;; (def r (part2 "day16.txt"))
;; (sort #(compare (first (keys %1)) (first (keys %2))) r)

(comment
  ** R 
(
  {17 (2)
  {
   :arrival-station 243,
   :departure-platform 242,
   :departure-time 242,
   :departure-station 242,
   :type 242,
   :route 242,
   :train 242,
   :departure-track 242,
   :departure-location 242,
   :departure-date 242,
   :arrival-track 242,
   :row 243,}}

  {12 (2)
  {
   :arrival-station 236,
   :departure-platform 236,
   :departure-time 236,
   :departure-station 236,
   :type 236,
   :route 235,
   :train 236,
   :departure-track 236,
   :departure-location 235,
   :departure-date 236,
   :arrival-track 236,
   :row 236,}}

   {14 (3)
  {
   :arrival-station 241,
   :departure-platform 240,
   :departure-time 240,
   :departure-station 240,
   :type 241,
   :route 240,
   :train 240,
   :departure-track 240,
   :departure-location 240,
   :departure-date 240,
   :arrival-track 240,
   :row 241,}}

 {5
  {
   :arrival-station 242,
   :departure-platform 242,
   :departure-time 242,
   :departure-station 242,
   :type 242,
   :route 241,
   :train 242,
   :departure-track 241,
   :departure-location 241,
   :departure-date 242,
   :arrival-track 242,
   :row 242,}
  }

 {8
  {
   :arrival-station 244,
   :departure-platform 243,
   :departure-time 244,
   :departure-station 244,
   :type 244,
   :route 243,
   :train 244,
   :departure-track 243,
   :departure-location 243,
   :departure-date 243,
   :arrival-track 244,
   :row 244,}}

 {11
  {
   :arrival-station 241,
   :departure-platform 241,
   :departure-time 241,
   :departure-station 241,
   :type 241,
   :route 240,
   :train 241,
   :departure-track 240,
   :departure-location 240,
   :departure-date 240,
   :arrival-track 241,
   :row 241,}}

 {15
  {
   :arrival-station 243,
   :departure-platform 242,
   :departure-time 243,
   :departure-station 242,
   :type 243,
   :route 242,
   :train 243,
   :departure-track 242,
   :departure-location 242,
   :departure-date 242,
   :arrival-track 243,
   :row 243,}}

 {16
  {
   :arrival-station 243,
   :departure-platform 242,
   :departure-time 242,
   :departure-station 242,
   :type 243,
   :route 242,
   :train 243,
   :departure-track 242,
   :departure-location 242,
   :departure-date 242,
   :arrival-track 243,
   :row 243,}}

 {20
  {
   :arrival-station 244,
   :departure-platform 243,
   :departure-time 243,
   :departure-station 243,
   :type 244,
   :route 243,
   :train 244,
   :departure-track 243,
   :departure-location 243,
   :departure-date 243,
   :arrival-track 243,
   :row 244,}})

 {13 (0)
  {
   :arrival-station 240,
   :departure-platform 240,
   :departure-time 240,
   :departure-station 240,
   :type 240,
   :route 240,
   :train 240,
   :departure-track 240,
   :departure-location 240,
   :departure-date 240,
   :arrival-track 240,
   :row 240,}}


  {6 (0)
  {
   :arrival-station 241,
   :departure-platform 241,
   :departure-time 241,
   :departure-station 241,
   :type 241,
   :route 241,
   :train 241,
   :departure-track 241,
   :departure-location 241,
   :departure-date 241,
   :arrival-track 241,
   :row 241,}}

)
