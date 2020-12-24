(ns advent.day16-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day16 :refer [part1 part2]]))


(deftest part1-test
  (let [expected 71]
    (is (= expected (part1 "day16-example-part1.txt"))))

  (let [expected  23009]
    (is (= expected (part1 "day16.txt")))))

(deftest part2-test
  (let [expected 1716]
    (is (= expected (part2 "day16-example-part2.txt"))))

  (let [expected 10458887314153]
    (is (= expected (part2 "day16.txt")))))
