(ns advent.day02-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day02 :refer [part1]]))


(deftest part1-test
  (let [expected 2]
    (is (= expected (part1 "day02-example.txt"))))

  (let [expected 591]
    (is (= expected (part1 "day02.txt")))))
