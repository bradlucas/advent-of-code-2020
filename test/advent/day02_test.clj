(ns advent.day02-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day02 :refer [part1 part2]]))


(deftest part1-test
  (let [expected 2]
    (is (= expected (part1 "day02-example.txt"))))

  (let [expected 591]
    (is (= expected (part1 "day02.txt")))))


(deftest part2-test
  (let [expected 1]
    (is (= expected (part2 "day02-example.txt"))))

  (let [expected 335]
    (is (= expected (part2 "day02.txt")))))
