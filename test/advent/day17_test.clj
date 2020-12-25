(ns advent.day17-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day17 :refer [part1]]))


(deftest part1-test
  (let [expected 112]
    (is (= expected (part1 "day17-example.txt"))))

  (let [expected  338]
    (is (= expected (part1 "day17.txt")))))

;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part2 "day17-example.txt"))))

;;   (let [expected 0]
;;     (is (= expected (part2 "day17.txt")))))
