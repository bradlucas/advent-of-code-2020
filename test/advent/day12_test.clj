(ns advent.day12-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day12 :refer [part1]]))


(deftest part1-test
  (let [expected 25]
    (is (= expected (part1 "day12-example.txt"))))

  (let [expected 1631]
     (is (= expected (part1 "day12.txt"))))
  )

;; (deftest part2-test
;;   (let [expected 26]
;;     (is (= expected (part2 "day12-example.txt"))))

;;   (let [expected 1897]
;;     (is (= expected (part2 "day12.txt")))))
