(ns advent.day06-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day06 :refer [part1 part2]]))


(deftest part1-test
  (let [expected 11]
    (is (= expected (part1 "day06-example.txt"))))

  (let [expected 6504]
     (is (= expected (part1 "day06.txt"))))
  )



(deftest part2-test
  (let [expected 6]
    (is (= expected (part2 "day06-example.txt"))))

  (let [expected 3351]
    (is (= expected (part2 "day06.txt"))))
  )
