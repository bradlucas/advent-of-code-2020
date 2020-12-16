(ns advent.day13-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day13 :refer [part1]]
            [advent.day13-2 :refer [part2]]))


(deftest part1-test
  (let [expected 295]
    (is (= expected (part1 "day13-example.txt"))))

  (let [expected 3606]
     (is (= expected (part1 "day13.txt")))))

(deftest part2-test
  (let [expected 1068781]
    (is (= expected (part2 "day13-example.txt"))))

  (let [expected 379786358533423]
    (is (= expected (part2 "day13.txt")))))
