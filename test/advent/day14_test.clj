(ns advent.day14-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day14 :refer [part1]]))


(deftest part1-test
  (let [expected 165]
    (is (= expected (part1 "day14-example.txt"))))

  (let [expected 3059488894985]
     (is (= expected (part1 "day14.txt")))))

;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part2 "day14-example.txt"))))

;;   (let [expected 0]
;;     (is (= expected (part2 "day14.txt")))))
