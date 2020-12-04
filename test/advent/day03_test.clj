(ns advent.day03-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day03 :refer [part1 part2]]))


(deftest part1-test
  (let [expected 7]
    (is (= expected (part1 "day03-example.txt"))))

  (let [expected 162]
    (is (= expected (part1 "day03.txt")))))


(deftest part2-test
  (let [expected 336]
    (is (= expected (part2 "day03-example.txt"))))

  (let [expected 3064612320]
    (is (= expected (part2 "day03.txt")))))
