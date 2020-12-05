(ns advent.day05-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day05 :refer [part1 part2]]))


(deftest part1-test
  (let [expected 820]
    (is (= expected (part1 "day05-example.txt"))))

  (let [expected 855]
     (is (= expected (part1 "day05.txt"))))
  )


(deftest part2-test
  (let [expected 552]
    (is (= expected (part2 "day05.txt")))))
