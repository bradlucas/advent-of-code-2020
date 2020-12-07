(ns advent.day07-test
  (:require [clojure.test :refer [deftest is]]
            [advent.day07 :refer [part1]]))


(deftest part1-test
  (let [expected 0]
    (is (= expected (part1 "day07-example.txt"))))

  (let [expected 0]
     (is (= expected (part1 "day07.txt"))))
  )



;; (deftest part2-test
;;   (let [expected 0]
;;     (is (= expected (part2 "day07-example.txt"))))

;;   (let [expected 0]
;;     (is (= expected (part2 "day07.txt"))))
;;   )
