(ns advent.day19-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day19 :refer [part1]]))


(deftest part1-test
  (let [expected 0]
    (is (= expected (part1 "day19-example.txt"))))

  (let [expected 0]
    (is (= expected (part1 "day19.txt")))))

;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part2 "day19-example.txt"))))

;;   (let [expected 0]
;;     (is (= expected (part2 "day19.txt")))))
