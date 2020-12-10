(ns advent.day10-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day10 :refer [part1]]))


(deftest part1-test
  (let [expected 220]
    (is (= expected (part1 "day10-example.txt"))))

  (let [expected 1625]
     (is (= expected (part1 "day10.txt"))))
  )


;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part2 "day10-example.txt" (part1 "day10-example.txt)))))

;;   (let [expected 0]
;;     (is (= expected (part2 "day10.txt" (part1 "day10.txt")))))
;;  )
