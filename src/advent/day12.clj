(ns advent.day12
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.math.combinatorics :as combo]

            [clojure.string :as str]))


;; | S | move | south   | by the given value.                                               |
;; | E | move | east    | by the given value.                                               |
;; | W | movye | west    | by the given value.                                               |
;; | L | turn | left    | the given number of degrees.                                      |
;; | R | turn | right   | the given number of degrees.                                      |
;; | F | move | forward | by the given value in the direction the ship is currently facing. |


;; read input into list of command values
;; map over action routine

(defn parse [s]
  ;; split into command (single letter) and value
  (let [command (keyword (str (first s)))
        val (Integer/parseInt (apply str (rest s)))]
    [command val]))


(defn read-input [file]
  (map #(parse %) (c/read-lines file)))


(def right-turns {:N :E
                  :E :S
                  :S :W
                  :W :N})


(def left-turns {:E :N
                 :S :E
                 :W :S
                 :N :W})


(defn turn [dir type value]
  (let [degrees {90 1 180 2 270 3}
        steps (get degrees value)]
    ;; find pos in directions, then add value to the index
    ;; return new value
    (let [turns (if (= type :R)
                  right-turns
                  left-turns)]
      (loop [cnt steps
             dir dir]
        (if (= cnt 0)
          dir
          (recur (dec cnt)
                 (get turns dir)))))))



(defn add-pos [[x y] dir value]
  (case dir
    :N [(- x value) y]
    :E [x (+ y value)]
    :S [(+ x value) y]
    :W [x (- y value)])
)


(defn move [[pos dir] [command value]]
  ;; apply command
  ;; return new pos
  ;; (pp/pprint (format "[%s %s] [%s %s]" pos dir command value))
  (let [[x y] pos]
  (case command
    :N [[(- x value) y]         dir]
    :S [[(+ x value) y]         dir]
    :E [[x (+ y value)]         dir]
    :W [[x (- y value)]         dir]
    :L [pos                     (turn dir :L value)]
    :R [pos                     (turn dir :R value)]
    :F [(add-pos pos dir value) dir])))



;; calc manhattan distance
;; diff x + diff y


(defn part1 [file]
  (let [[[x y] dir] (reduce move [[0 0] :E] (read-input file))]
    (+ x y)))
