(ns advent.day16-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day16 :refer [part1]]))


(deftest part1-test
  (let [expected 71]
    (is (= expected (part1 "day16-example.txt"))))

    (let [expected  23009]
      (is (= expected (part1 "day16.txt")))))

;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part1 "day16-example.txt"))))

;;     (let [expected  0]
;;       (is (= expected (part1 "day16.txt")))))
