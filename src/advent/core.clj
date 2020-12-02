(ns advent.core
  (:require [clojure.java.io :as io])
  (:gen-class))


(defn read-input [day]
  (slurp (io/resource day)))

(defn read-lines [day]
  (with-open [rdr (io/reader (io/resource day))]
    (doall (line-seq rdr))))



(defn -main
  [& args]
  (do
    (println "Advent of Code 2020")
    (println "See each day's file")))
