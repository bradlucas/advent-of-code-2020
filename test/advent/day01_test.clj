(ns advent.day01-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day01 :refer [part1 part2]]))



(comment

  advent.day01> (part1 "day01-example.txt")
  514579
  advent.day01> (part1 "day01.txt")
  1005459
  advent.day01> (part2 "day01-example.txt")
  241861950
  advent.day01> (part2 "day01.txt")
  92643264

  )


(deftest part1-test
  (let [expected 514579]
    (is (= expected (part1 "day01-example.txt"))))

  (let [expected 1005459]
    (is (= expected (part1 "day01.txt")))))



(deftest part2-test
  (let [expected 241861950]
    (is (= expected (part2 "day01-example.txt"))))

  (let [expected 92643264]
    (is (= expected (part2 "day01.txt")))))
