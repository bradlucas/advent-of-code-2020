(ns advent.day14
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))



(defn parse-line [s]
  (let [[c v] (-> s
                  (str/split #" = "))]
    (if ( = c "mask")
      {:cmd :mask
       :val v}
      (let [[_ a] (-> c
                      (str/split #"[\[\]]"))]
        {:cmd :mem
         :addr (Integer/parseInt a)
         :val (Integer/parseInt v)}))))


(defn left-pad [num s]
  ;; left pad the s string with 0s until the value is num characters long
  (let [cnt (- num (count s))]
    (apply str (reverse (concat (reverse s) (repeat cnt \0))))))

(defn mask-val [mask val]
  (let [vs (Integer/toBinaryString val)
        vs2 (left-pad 36 vs)]
    ; merge strings
    ;; overwritw 0,1 from mask, ignore if X
    (apply str (map (fn [a b] (if (= \X a) b a)) mask vs2))))

(defn process-value [mask acc cmd]
  (let [addr (:addr cmd)
        val (:val cmd)]
    (assoc acc addr (mask-val mask val))))

(defn process [lines]
  (loop [l lines
         mask nil
         acc {}]
    (if (empty? l)
      acc
      ;; if mask set m
      ;; elsle apply m to meme
      (do
        (let [rtn (parse-line (first l))
            mask (if (= :mask (:cmd rtn)) (:val rtn) mask)
            acc (if (not (= :mask (:cmd rtn))) (process-value mask acc rtn) acc)]
        (recur (rest l)
               mask
               acc))))))

(defn binary-string->number [s]
  (Long/parseLong s 2))

(defn add-values [l]
  (apply + (map binary-string->number l)))



(defn part1 [file]
  (add-values (vals (process (c/read-lines file)))))

