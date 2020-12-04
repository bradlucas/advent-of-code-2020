(ns advent.day04-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day04 :refer [part1]]))


(deftest part1-test
  (let [expected 2]
    (is (= expected (part1 "day04-example.txt"))))

  (let [expected 260]
    (is (= expected (part1 "day04.txt"))))
  )
