(ns advent.day18
  (:require [advent.core :as c]
            [clojure.pprint :as pp]
            [clojure.string :as str]))


;; @see https://github.com/nbardiuk/adventofcode/blob/master/2020/src/day18.clj

(def example "1 + 2 * 3 + 4 * 5 + 6")
(def example2 "1 + (2 * 3) + (4 * (5 + 6))")


(defn parse [s]
  (->> s
       (re-seq #"\d+|[()+*]")
       (map #(case %
               "(" :open
               ")" :close
               "+" :plus
               "*" :times
               (Integer/parseInt %)))))    ;; could use (read-string %)


(defn eval [tokens]
  (letfn [(element [[token & tokens]]
            (case token
              :open (let [[a [_close & tokens]] (pair tokens)]
                      [a tokens])
              [token tokens]))

          (pair [tokens]
            (let [[a [op & tokens] :as left] (element tokens)
                  [b tokens] (element tokens)]
              (case op
                :plus (pair (cons (+ a b) tokens))
                :times (pair (cons (* a b) tokens))
                left)))]
    (first (pair tokens))))


;; (eval (parse example)) => 71
;; (eval (parse example2)) => 51


(defn part1 [file]
  (->> (c/read-lines file)
       (map parse)
       (map eval)
       (reduce +)))


;; (part1 "day18.txt") => 24650385570008



;; ----------------------------------------------------------------------------------------------------
;; part2

;; addition is evaluated before multiplication



(defn eval2 [tokens]
  (letfn [(element [[token & tokens]]
            (case token
              :open (let [[a [_close & tokens]] (mult tokens)]
                      [a tokens])
              [token tokens]))

          (mult [tokens]
            (let [[a [op & tokens] :as left] (add tokens)]
              (case op
                :times (let [[ b tokens] (mult tokens)]
                         [(* a b) tokens])
                left)))

          (add [tokens]
            (let [[a [op & tokens] :as left] (element tokens)]
              (case op
                :plus (let [[ b tokens] (add tokens)]
                        [(+ a b) tokens])
                left)))]
    (first (mult tokens))))


;; (eval2 (parse example)) => 231
;; (eval2 (parse example2)) => 51


(defn part2 [file]
  (->> (c/read-lines file)
       (map parse)
       (map eval2)
       (reduce +)))


;; (part2 "day18.txt") =>
