(ns advent.day10-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day10 :refer [part1 part2]]))


(deftest part1-test
  (let [expected 220]
    (is (= expected (part1 "day10-example.txt"))))

  (let [expected 1625]
     (is (= expected (part1 "day10.txt"))))
  )

(deftest part2-test
  (let [expected 8]
    (is (= expected (part2 "day10-example0.txt"))))

  (let [expected 19208]
    (is (= expected (part2 "day10-example.txt"))))

  (let [expected 3100448333024]
    (is (= expected (part2 "day10.txt")))))
