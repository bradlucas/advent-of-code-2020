(ns advent.day13
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


(defn parse [s]
  (filter #(not (nil? %)) (map (fn [x] (if (not (= x "x")) (Integer/parseInt x))) (str/split s #","))))

(defn read-input [file]
  ;; first line is the estimated start time
  ;; next line are the bus ids, ignore x,
  (let [l (c/read-lines file)
        t (Integer/parseInt (first l))
        b (parse (second l))]
    {:start t
     :buses b}))

;; @see https://groups.google.com/g/clojure-dev/c/NaAuBz6SpkY
(defn take-until
  "Returns a lazy sequence of successive items from coll until
   (pred item) returns true, including that item. pred must be
   free of side-effects."
  [pred coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (if (pred (first s))
        (cons (first s) nil)
        (cons (first s) (take-until pred (rest s)))))))

(defn find-bus [buses t]
  (first (filter #(zero? (:mod %)) (map (fn [v] {:bus v
                                                 :end t
                                                 :mod (mod t v)}) buses))))


(defn part1 [file]
  (let [{:keys [start buses]}(read-input file)]
    (let [{:keys [bus end _]} (first (filter #(not (nil? %)) (map (partial find-bus buses) (iterate inc start))))]
      (* bus (- end start)))))
