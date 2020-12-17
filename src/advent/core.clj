(ns advent.core
  (:require [clojure.java.io :as io])
  (:gen-class))


(defn read-input [day]
  (slurp (io/resource day)))

(defn read-lines [day]
  (with-open [rdr (io/reader (io/resource day))]
    (doall (filter #(not (empty? %)) (line-seq rdr)))))

(defn read-numbers [day]
  (->> day
       (read-lines)
       (filter #(not (empty? %)))
       (map #(read-string %))))


(defn -main
  [& args]
  (do
    (println "Advent of Code 2020")
    (println "See each day's file")))
