(ns advent.day04
  (:require [advent.core :as c]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


(defn build-maps [s]
  (loop [wrds (str/split (str/replace s #"\n" " ") #" ")
         acc {}]
    (if (empty? wrds)
      acc
      (let [[k v] (str/split (first wrds) #":")]
        (recur (rest wrds) (assoc acc (keyword k) v))))))


(defn read-input [file]
  (-> file
       (io/resource)
       (slurp)
       (str/split #"\n\n")
       ((partial map build-maps))
       ))


;; Valid password has all eight fields
;; Valid if only if missing the cid field



;; byr (Birth Year)
;; iyr (Issue Year)
;; eyr (Expiration Year)
;; hgt (Height)
;; hcl (Hair Color)
;; ecl (Eye Color)
;; pid (Passport ID)
;; cid (Country ID)


(def fields (set [:byr
                  :iyr
                  :eyr
                  :hgt
                  :hcl
                  :ecl
                  :pid
                  :cid]))


(defn valid [m]
  (let [m-fields (set (keys m))]
    (or (= fields m-fields)
        (= (disj fields :cid) m-fields ))))


(defn part1 [file]
  (->> file
      read-input
      (filter #(valid %))
      count))



;; ----------------------------------------------------------------------------------------------------
;; Part2

(defn all-digits [cnt s]
  (let [lst (seq s)]
    (if (= cnt (count lst))
      (every? true? (map #(Character/isDigit %) lst)))))

(defn valid-year [s n l h]
  (and (all-digits n s)
       (let [n (Integer/parseInt s)]
         (and (>= n l)
              (<= n h)))))

(defn valid-birth-year [s]
  ;; Four digits at least 1920 and at most 2002
  (valid-year s 4 1920 2002))

(defn valid-issue-year [s]
  ;; Four digits at least 2010 and at most 2020
  (valid-year s 4 2010 2020))

(defn valid-expiration-year [s]
  ;; four digits at least 2020 and at most 2030
  (valid-year s 4 2002 2030))

(defn valid-height [s]
  ;; A number followed by either cm or in:
  ;;  If cm, the number must be at least 150 and at most 193
  ;;  If in, the number must be at least 59 and at most 76
  ;; Can have a missing cm/in which makes the value invalid as well
  (let [len (count s)
        [n t] (map #(apply str %) (split-at (- len 2) (seq s)))]
    (if (or (= t "cm") (= t "in"))
      (let [num (Integer/parseInt n)]
        (if (= t "cm")
          (and (>= num 150)
               (<= num 193))
          (if (= t "in")
            (and (>= num 59)
                 (<= num 76))))))))

(defn valid-hair-color [s]
  ;; A # followed by exactly six characters 0-9 or a-f
  (and (= \# (get s 0))
       (= 7 (count s))
       (= 6 (count (re-seq #"[a-z0-9]" (apply str (rest s)))))))

(defn valid-eye-color [s]
  ;; Exactly one of: amb blu brn gry grn hzl oth
  (let [k (keyword s)
        colors #{:amb :blu :brn :gry :grn :hzl :oth}]
    (colors k)))

(defn num-digits [s]
  (count (map true? (map #(Character/isDigit %) (seq s)))))

(defn valid-password [s]
  ;; A nine-digit number, including leading zeroes
  (= 9 (num-digits s)))


(defn extended-valid [m]
  (and (valid m)
       (valid-birth-year (:byr m))
       (valid-issue-year (:iyr m))
       (valid-expiration-year (:eyr m))
       (valid-height (:hgt m))
       (valid-hair-color (:hcl m))
       (valid-eye-color (:ecl m))
       (valid-password (:pid m))))


(defn part2 [file]
  (->> file
       read-input
       (filter #(extended-valid %))
       count))
