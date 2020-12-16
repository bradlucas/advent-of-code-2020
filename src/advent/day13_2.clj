(ns advent.day13-2
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


(defn parse [s]
  ;; parse into
  ;; {:index 0
  ;;  :bus 1}
  (filter #(not (nil? %)) (map (fn [a b] (if (not (= b "x")) {:index a :bus (Integer/parseInt b)})) (range) (str/split s #","))))

(defn read-input [file]
  ;; first line is the estimated start time
  ;; next line are the bus ids, ignore x,
  (let [lines (c/read-lines file)
        buses (parse (second lines))]
    buses))


;; ----------------------------------------------------------------------------------------------------
;; Offset t for each index
;;
;; | t   | mod 7  | 0 |
;; | t+1 | mod 13 | 0 |
;; | t+4 | mod 59 | 0 |
;; | t+6 | mod 31 | 0 |
;; | t+7 | mod 19 | 0 |

(defn find-bus [buses t]
  (let [rtn (map (fn [bus]
                   (let [t2 (+ t (:index bus))]
                     {:bus (:bus bus)
                      :end t2
                      :mod (mod t2 (:bus bus))})) buses)]
    (pp/pprint rtn)
    (if (= #{0} (into #{} (map :mod rtn)))
      rtn)))

(defn run [buses start]
  ;; start is like a hint
  ;; increment by the first bus
  (map (partial find-bus buses) (iterate (fn [x] (+ x (:bus (first buses)))) start)))

;; This doesn't complete...
(defn part2-0 [file start]
  (let [buses (read-input file)
        ans (concat [0] (map #(- (:bus %) (:index %)) (rest buses)))]
    (filter #(= ans %) (run buses start))))

(def start 100000000000000)


;; ----------------------------------------------------------------------------------------------------
;; @see https://www.reddit.com/r/adventofcode/comments/kc5a23/2020_day_13_part_2_chinese_remainder_theorem/gfobemn?utm_source=share&utm_medium=web2x&context=3
;;
;; | t mod 7  |  0 | 0 |        |
;; | t mod 13 | 12 | 1 | 13 - 1 |
;; | t mod 59 | 55 | 4 | 59 - 4 |
;; | t mod 31 | 25 | 6 | 31 - 6 |
;; | t mod 19 | 12 | 7 | 19 - 7 |


(defn find-bus2 [buses t]
  {:t t
   :r (map #(mod t %) (map :bus buses))})

(defn run2 [buses]
  (map (partial find-bus2 buses) (iterate (fn [x] (+ x (:bus (first buses)))) 0)))

(defn part2-1 [file]
  (let [buses (read-input file)
        ans (concat [0] (map #(- (:bus %) (:index %)) (rest buses)))]
    (filter #(= ans {:ans %}) (run2 buses))))




;; ----------------------------------------------------------------------------------------------------
;; Chinese Remainder Theorem
;; @see https://redpenguin101.github.io/posts/2020_12_13_mod_mult.html
;; 

(defn- linear-congruence-solve [pairs]
  (let [remainders (map first pairs)
        modulos (map second pairs)
        prod (apply * modulos)
        pp (map #(/ prod %) modulos)
        inv (map #(.modInverse (biginteger %1) (biginteger %2)) pp modulos)]
    (mod (apply + (map * remainders pp inv)) prod)))



;; (linear-congruence-solve [[2 3] [3 4] [1 5]]) => 3417N
;; (linear-congruence-solve (map (fn [a b] [a b]) ans (map :bus buses))) => 1068781N

(defn part2 [file]
  (let [buses (read-input file)
        ans (concat [0] (map #(- (:bus %) (:index %)) (rest buses)))]
    (linear-congruence-solve (map (fn [a b] [a b]) ans (map :bus buses)))))


;; (part2c "day13-example.txt")
;; 1068781N


;; (part2c "day13.txt")
;; 379786358533423N

