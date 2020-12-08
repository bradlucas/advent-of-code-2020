(ns advent.day08-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day08 :refer [part1]]))


(deftest part1-test
  (let [expected 5]
    (is (= expected (part1 "day08-example.txt"))))

  (let [expected 1446]
     (is (= expected (part1 "day08.txt"))))
  )


;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part2 "day08-example.txt"))))

;;   (let [expected 0]
;;     (is (= expected (part2 "day08.txt"))))
;;  )
