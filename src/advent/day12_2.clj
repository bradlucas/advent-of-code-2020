(ns advent.day12-2
  (:require [advent.core :as c]
            [clojure.pprint :as pp]))


(defn parse [s]
  ;; split into command (single letter) and value
  (let [command (keyword (str (first s)))
        val (Integer/parseInt (apply str (rest s)))]
    [command val]))

(defn read-input [file]
  (map #(parse %) (c/read-lines file)))


(defn turn [waypoint cmd]
  (let [[x y] waypoint
        [c v] cmd]
    (if (= 90 v)
      (if (= :R c)
        [y (* -1 x)]
        [(*  -1 y) x])
      (if (= 270 v)
        (if (= :R c)
          [(*  -1 y) x]
          [y (* -1 x)])
        ;; 180
        [(*  -1 x)
         (* -1 y)]))))


(defn move-ship [ship waypoint value]
  ;; ship + (value * waypoint)
  (let [[x y] ship
        [wx wy] waypoint]
    [(+ x (* wx value))
     (+ y (* wy value))]))


(defn move [[ship waypoint] cmd]
  (let [[x y] ship
        [wx wy] waypoint
        [command value] cmd]
    (case command
      :E [ship [(+ wx value) wy]]
      :W [ship [(- wx value) wy]]
      :N [ship [wx (+ wy value)]]
      :S [ship [wx (- wy value)]]
      :L [ship (turn waypoint cmd)]
      :R [ship (turn waypoint cmd)]
      :F [(move-ship ship waypoint value) waypoint])))

(defn abs [x]
  (if (< x 0)
    (* -1 x) x))


(defn part2 [file]
  ;; [East North]
  (let [[ship waypoint] (reduce move [[0 0] [10 1]] (read-input file))]
    (let [[x y] ship]
      (+ (abs x) (abs y)))))
