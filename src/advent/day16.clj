(ns advent.day16
  (:require [advent.core :as c]
            [clojure.set :as s]
            [clojure.pprint :as pp]
            [clojure.java.io :as io]
            [clojure.string :as str]))

;; ----------------------------------------------------------------------------------------------------
;; @see rewritten after studying https://github.com/nbardiuk/adventofcode/blob/master/2020/src/day16.clj

(defn parse-number-list [s]
  (map #(Integer/parseInt %) (str/split s #",")))

(defn parse-num-pairs [s]
  (map #(Integer/parseInt %) (str/split s #"-")))

(defn parse-rules [s]
  (let [[n d] (str/split s #":")
        d (-> d
              str/trim
              (str/split #" or "))
        data (flatten (map parse-num-pairs d))]
    {(keyword (keyword (str/replace n " " "-")))
     data}))

(defn read-input [day]
  (let [[rules ticket nearby] (str/split (c/read-input day) #"\n\n")]    ;; three sections delimated by \n\n
    {:rules (into {} (map parse-rules (str/split rules #"\n")))
     :ticket (parse-number-list (second (str/split ticket #"\n")))
     :nearby (map parse-number-list (rest (str/split nearby #"\n")))}))

;; rules 1-3 or 5-7  => (or (<= a VAL b) (<= c d))
(defn match-rule [[a b c d] value]
  (or (<= a value b) (<= c value d)))

(defn matches-a-rule [rules value]
  (some #(match-rule % value) (vals rules)))

(defn part1 [file]
  (let [{:keys [rules ticket nearby]} (read-input file)]
    ;; remove all seats from nearby that match one of the rules
    ;; then add the resulting seat numbers
    (->> (flatten nearby)
         (remove (fn [v] (matches-a-rule rules v)))
         (apply +))))

;; day16-example-part1.txt => 71
;; day16.txt => 23009

;; ----------------------------------------------------------------------------------------------------
;; Part 2

(def matches-rule match-rule)
(def matches-rules matches-a-rule)

;; ----------------------------------------------------------------------------------------------------
;; rewrite to learn

(defn valid-ticket [rules ticket]
  ;; all seats in a ticket (a b c) are valid 
  (every? true? (map #(matches-a-rule rules %) ticket)))

(defn valid-tickets [rules tickets]
  (filter #(valid-ticket rules %) tickets))


;; ----------------------------------------------------------------------------------------------------
;; @see this block is from  https://github.com/nbardiuk/adventofcode/blob/master/2020/src/day16.clj
;; comments added are mine


(defn- keep-matching-rules [rules value]
  ;; return rules that match the value
  (reduce
   (fn [rules [field ranges]]
     (if (matches-rule ranges value)
       rules
       (dissoc rules field)))
   rules rules))

;; (defn- valid-tickets [rules tickets]
;;   (filter #(every? (partial matches-rules rules) %) tickets))

(defn- fix-point [f x]
  (->> (iterate f x)
       (partition 2 1)
       (drop-while #(apply not= %))
       ffirst))

(defn- drop-decided [candidates]
  ;; remove the decided rule from other candidates
  (let [decided? #(= 1 (count %))
        decided-fields (->> candidates (filter decided?) (map keys) flatten)]
    (for [candidate candidates]
      (if (decided? candidate)
        candidate
        (apply dissoc candidate decided-fields)))))

;; Each ticket is passed in with a result of the previous call
;; narrowing the candidates
(defn- narrow-candidates [candidates ticket]
  (->> (map keep-matching-rules candidates ticket)
       (fix-point drop-decided)))

;; put the fields in order
(defn- resolve-fields [{:keys [rules nearby]}]
  (let [candidates (repeat (count rules) rules)]
    (->> (valid-tickets rules nearby) ;; filter out the valid tickets
         ;; for each of the valid tickets (nearby)
         ;; (reduce function val coll)
         ;; (reduce narrow-candidates canditates valid-tickets-coll)
         ;; If val is supplied, returns the
         ;; result of applying f to val and the first item in coll, then
         ;; applying f to that result and the 2nd item, etc.
         (reduce narrow-candidates candidates)         
         (map (comp first keys)) ;; pull out the keys from the list of hashmaps (map #(first (keys %))
         )))

;; ----------------------------------------------------------------------------------------------------

;; day16-example-part2.txt  => 1716
;; day16.txt => 10458887314153

(defn part2 [file]
  (let [data (read-input file)
        ticket (zipmap (resolve-fields data) (:ticket data))]
    (reduce * (map ticket (filter #(str/starts-with? (name %) "departure") (keys ticket))))))
