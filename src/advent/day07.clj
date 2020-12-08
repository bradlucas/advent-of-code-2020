(ns advent.day07
  (:require [advent.core :as c]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [clojure.set :as set]
            [clojure.string :as str]))


;; Example
(def graph
  {:light-red {:bright-white 1 :yellow 2}
   :dark-orange {:bright-white-bags 3 :muted-yellow 4}
   :bright-white {:shiny-gold 1}
   :muted-yellow {:shiny-gold 2 :faded-blue 9}
   :shiny-gold  {:dark-olive 1  :vibrant-plum 2}
   :dark-olive   {:faded-blue 3 :dotted-black 4}
   :vibrant-plum  {:faded-blue 5 :dotted-black 6}
   :faded-blue {}
   :dotted-black {}
   }
  )

;; light-red -> bright-white -> shiny-gold -> 2
;; Walk each root till you get to a shiny-gold
;; Return the number of paths found
;; Ignore :shingy-gold root
;; light red bags contain 1 bright white bag, 2 muted yellow bags.
;; ["light red bags" "1 bright white bag, 2 muted yellow bags."]

(defn make-keyword [lst]
  (keyword (str (first lst) "-" (second lst))))

(defn parse-contents [s]
  (let [s (str/trim s)
        [num a b] (str/split s #" ")]
    {(make-keyword [a b])
     (Integer/parseInt num)}))

(defn build-contents [s]
  (if (= s "no other bags.")
    nil
    (into {} (map parse-contents (str/split s #",")))))

(defn parse-bag [[b contents]]
  (let [bags (-> b
                 (str/split #" ")
                 (make-keyword))
        contents (build-contents contents)]
    {bags contents}))

(defn parse-line [s]
  (-> s
      (str/split #" contain ")
      (parse-bag)))

(defn read-input [file]
  (->> file
       (c/read-lines)))


;; @see https://codereview.stackexchange.com/a/16010
(defn- dfs
  [graph goal]
  (fn search
    [path visited]
    (let [current (peek path)]
      (if (= goal current)
        [path]
        (->> current graph keys
             (remove visited)
             (mapcat #(search (conj path %) (conj visited %))))))))

(defn findpath
  "Returns a lazy sequence of all directed paths from start to goal
  within graph."
  [graph start goal]
  ((dfs graph goal) [start] #{start}))


(defn part1 [file]
  (let [graph (into {} (map parse-line (read-input file)))]
    (count (filter #(not (empty? %)) (map #(findpath graph % :shiny-gold)  (remove #(= :shiny-gold %) (keys graph)))))))
