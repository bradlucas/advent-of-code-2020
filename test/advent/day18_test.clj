(ns advent.day18-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day18 :refer [part1]]))


(deftest part1-test
  (let [expected 13632]
    (is (= expected (part1 "day18-example.txt"))))

  (let [expected 24650385570008]
    (is (= expected (part1 "day18.txt")))))

;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part2 "day18-example.txt"))))

;;   (let [expected 0]
;;     (is (= expected (part2 "day18.txt")))))
