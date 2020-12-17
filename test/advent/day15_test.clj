(ns advent.day15-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day15 :refer [part1]]))


(deftest part1-test
  (let [expected 436]
    (is (= expected (part1 [0,3,6]))))

    (let [expected 0]
      (is (= expected (part1 [0,3,1,6,7,5])))))


;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part2)))))
