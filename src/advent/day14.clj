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





;; ----------------------------------------------------------------------------------------------------
;; Part2

(defn parse-line2 [s]
  (let [[c v] (-> s
                  (str/split #" = "))]
    (if ( = c "mask")
      {:cmd :mask
       :val v}
      (let [[_ a] (-> c
                      (str/split #"[\[\]]"))]
        {:cmd :mem
         :addr (left-pad 36 (Integer/toBinaryString (Integer/parseInt a)))
         :val (Integer/parseInt v)}))))


;; @see https://github.com/bnii/advent-of-code-2020/blob/main/src/day14.clj
;; Added (apply str s) to return values as binary strings
(defn floating->concretes [floating-location]
  (loop [current-locations [[]]
         [actual-bit & remainder-bits] floating-location]
    (cond
      (nil? actual-bit) (map (fn [s] (apply str s))current-locations)
      (#{\0 \1} actual-bit) (recur (map #(conj % actual-bit) current-locations) remainder-bits)
      :else (recur (concat
                     (map #(conj % \1) current-locations)
                     (map #(conj % \0) current-locations))
                   remainder-bits))))


(defn mask-addr [mask val]
  (let [result (apply str (map   (fn [a b] (if (= \0 a)
                           b
                           (if (= \1 a)
                             \1
                             \X)))
                            mask val))]
    ;; Purmutations of result replacing all Xs with 0 or 1
    (floating->concretes result)))

(defn process-value2 [m acc i]
  (let [addr (:addr i)
        val (:val i)]
    ;; mask the addr
    (let [addresses (mask-addr m addr)]
      ;; load the addresses with the val
      (loop [a addresses
             acc acc]
        (if (empty? a)
          [m acc]
          (recur (rest a) (assoc acc (first a) val)))))))

(defn process-line [m acc i]
  (if (= :mask (:cmd i))
    [(:val i) acc]
    (process-value2 m acc i)))


(defn part2 [file]
  (let [instructions (map parse-line2 (c/read-lines file))]
    (loop [i instructions
           mask nil
           acc {}]
      (if (empty? i)
        (apply + (vals acc))
        (let [[m a] (process-line mask acc (first i))]
          (recur (rest i) m a))))))




