(ns advent.day15-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day15-2 :refer [part1 part2]]))


(deftest part1-test
  (let [expected 436]
    (is (= expected (part1 [0,3,6]))))

    (let [expected 852]
      (is (= expected (part1 [0,3,1,6,7,5])))))

(deftest part2-test
  (let [expected 6007666]
    (is (= expected (part2)))))
