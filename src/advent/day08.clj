(ns advent.day08
  (:require [advent.core :as c]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [clojure.set :as set]
            [clojure.string :as str]))


(defn parse-instruction [[i v]]
  [(keyword i)
   (Integer/parseInt v)])

(defn read-input [file]
  (->> file
      (c/read-lines)
      (map #(str/split % #" "))
      (map parse-instruction)))

(defn visited? [lst val]
  (some #{val} lst))

(defn move-next [program idx acc]
  (let [[instruction val] (nth program idx)]
    (case instruction
      :nop [(inc idx) acc]
      :jmp [(+ idx val) acc]
      :acc [(inc idx) (+ acc val)])))

(defn run [program]
  (loop [[idx acc] [0 0]
         visited []]
    (if (visited? visited idx)
      acc
      (recur (move-next program idx acc) (conj visited idx)))))

(defn part1 [file]
  (run (read-input file)))



;; ----------------------------------------------------------------------------------------------------

;; change one jmp to nop, or one nop to jmp to fix the program
;; valid run is when the idx goes past the last instruction

;; try every combination of the program where nop/jmp is swapped


(comment
  ([:nop 0]
   [:acc 1]
   [:jmp 4]
   [:acc 3]
   [:jmp -3]
   [:acc -99]
   [:acc 1]
   [:jmp -4]
   [:acc 6])
  )

(defn get [program idx]
  (nth program idx))

(defn set [program idx val]
  (assoc program idx val))

(defn swap [[a b]]
  (if (= :nop a)
    [:jmp b]
    [:nop b]))

(defn build-version [program idx]
  (set program idx (swap (get program idx))))

(defn nopjmp? [program idx]
  (let [[a b] (get program idx)]
    (or (= :nop a) (= :jmp a))))

(defn get-indexes [program]
  (let [cnt (count program)]
    (loop [idx 0
           acc []]
      (if (>= idx cnt)
        acc
        (recur (inc idx)
               (if (nopjmp? program idx) (conj acc idx) acc))))))

(defn build-alternative-programs [program]
    (map #(build-version program %) (get-indexes program)))

(defn run2
  "Exit when the program runs past the last instruction."
  [program]
  (let [cnt (count program)]
    (loop [[idx acc] [0 0]
           visited []]
      (if (visited? visited idx)
        nil
        (if (>= idx cnt)
          acc
          (recur (move-next program idx acc) (conj visited idx)))))))


(defn part2 [file]
  (let [p (vec (read-input file))
        programs (build-alternative-programs p)]
    (first (filter #(not (nil? %)) (map run2 programs)))))
