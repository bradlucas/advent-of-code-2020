(ns advent.day04-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day04 :refer [part1 part2]]))


(deftest part1-test
  (let [expected 2]
    (is (= expected (part1 "day04-example.txt"))))

  (let [expected 260]
    (is (= expected (part1 "day04.txt")))))


(deftest part2-test
  (let [expected 4]
    (is (= expected (part2 "day04-2-example.txt"))))

  (let [expected 153]
    (is (= expected (part2 "day04.txt")))))
