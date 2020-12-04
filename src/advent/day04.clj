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
