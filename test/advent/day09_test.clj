(ns advent.day09-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day09 :refer [part1 part2]]))


(deftest part1-test
  (let [expected 127]
    (is (= expected (part1 "day09-example.txt" 5))))

  (let [expected 57195069]
     (is (= expected (part1 "day09.txt" 25))))
  )


(deftest part2-test
  (let [expected 62]
    (is (= expected (part2 "day09-example.txt" (part1 "day09-example.txt" 5)))))

  (let [expected 7409241]
    (is (= expected (part2 "day09.txt" (part1 "day09.txt" 25)))))
 )
