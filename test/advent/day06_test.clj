(ns advent.day06-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day06 :refer [part1]]))


(deftest part1-test
  (let [expected 11]
    (is (= expected (part1 "day06-example.txt"))))

  (let [expected 6504]
     (is (= expected (part1 "day06.txt"))))
  )
