(ns advent.day11-2
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.math.combinatorics :as combo]
            [clojure.string :as str]))


;; L == empty
;; # == occupied
;; . == floor

;; L.LL.LL.LL
;; LLLLLLL.LL
;; L.L.L..L..
;; LLLL.LL.LL
;; L.LL.LL.LL
;; L.LLLLL.LL
;; ..L.L.....
;; LLLLLLLLLL
;; L.LLLLLL.L
;; L.LLLLL.LL


;; (defn read-input [file]
;;   (mapv #(mapv identity %) (str/split (c/read-input file) #"\n")))

(defn positions
  [m]
  (let [get-height-width (fn [m] {:height (count m)
                                  :width (count (first m))})
        {:keys [height width]} (get-height-width m)]
    (for [x (range height)
          y (range width)]
        [x y])))

(defn read-input
  "Build a map of the positions where the position is the key."
  [file]
  (let [data (mapv #(mapv identity %) (str/split (c/read-input file) #"\n"))]
    (loop [pos (positions data)
           acc {}]
      (if (empty? pos)
        acc
        (let [[x y] (first pos)]
          (recur (rest pos) (into acc {[x y] (get (get data x) y)})))))))

(defn print-grid [g]
  (let [size (int (Math/sqrt (count g)))]
    (for [x (range size)]
      (str/join "" (map #(str (get g [x %])) (range size))))))



;; RULES
;; if empty (L) and no occupied seats adjecent then occupy
;; if occupoed (#) and four or more adjacent are occupied then empty


;; map over the seats in the grid and apply the rule as you go


(def adjacent (combo/permuted-combinations [1 1 -1 -1 0] 2))

(defn add-point [[a b] [c d]]
  [( + a c)
   (+ b d)])

;; find first seat along a tangent
(defn find-first-value [g pos dir]
  ;; do until value or nil
  (loop [p (add-point pos dir)]
    (let [v (get g p)]
      (if v
        (if (not (= \. v))
          p
          (recur (add-point p dir)))))))

  
(defn neighbors-with-values [g p]
  ;; search along each diagonal and frist the first position with a value
  (select-keys g (filter identity (map #(find-first-value g p %) adjacent))))

(defn seat [g p]
  (g p))

(defn occupied [v]
  (= \# v))

(defn crowded [positions]
  ;; five or more \# values
  (>= (count (filter #(= \# %) (vals positions))) 5))

(defn empty [v]
  (= \L v))

(defn empty-neighbors [positions]
  ;; no \# in positions
  (= 0 (count (filter #(= \# %) (vals positions)))))

(defn apply-rules-position [g p]
  (let [positions (neighbors-with-values g p)
        s (seat g p)]
    (if (and (empty s) (empty-neighbors positions))
      {p \#}
      (if (and (occupied s) (crowded positions))
        {p \L}
        {p s}))))

(defn apply-rules [g]
  (into {} (map #(apply-rules-position g %) (sort (keys g)))))

(defn occupied-count [g]
  (count (filter #(= \# %) (vals g))))



(def p print-grid)




;; ----------------------------------------------------------------------------------------------------


;; consider the firest seat in each of the eight directions
;; move along each line and find the first in each direction

;; no it take five or more visible occupied seats to become empty


(defn part2 [file]
  ;; apply-rules until the count of occupied seats stays the same
  (loop [g (read-input file)
         prev 0]
    (let [g (apply-rules g)
          c (occupied-count g)]
      ;; (pp/pprint c)
      (if (= prev c)
        c
        (recur g c)))))
