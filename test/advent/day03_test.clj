(ns advent.day03-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day03 :refer [part1]]))


(deftest part1-test
  (let [expected 7]
    (is (= expected (part1 "day03-example.txt"))))

  (let [expected 162]
    (is (= expected (part1 "day03.txt")))))
