(ns advent.day11-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day11 :refer [part1]]))


(deftest part1-test
  (let [expected 37]
    (is (= expected (part1 "day11-example.txt"))))

  (let [expected 2108]
     (is (= expected (part1 "day11.txt"))))
  )

;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part2 "day11-example.txt"))))

;;   (let [expected 0]
;;     (is (= expected (part2 "day11.txt")))))
