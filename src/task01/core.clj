(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn find-res [data]
  (loop []
    (if (vector? data)
      (do
        (if (= (get (get data 1) :class) "r")
            [(get (get (get data 2) 1) :href)]

            (if (empty? (next (next data)))
              []
              (reduce #(concat % %2) (map #(find-res %) (next (next data))))
            )

          )
        )
      []
    )
  )
)

(defn get-links []
" 1) Find all elements containing {:class \"r\"}.

Example:
[:h3 {:class \"r\"} [:a {:shape \"rect\", :class \"l\",
                         :href \"https://github.com/clojure/clojure\",
                         :onmousedown \"return rwt(this,'','','','4','AFQjCNFlSngH8Q4cB8TMqb710dD6ZkDSJg','','0CFYQFjAD','','',event)\"}
                     [:em {} \"clojure\"] \"/\" [:em {} \"clojure\"] \" · GitHub\"]]

   2) Extract href from the element :a.

The link from the example above is 'https://github.com/clojure/clojure'.

  3) Return vector of all 10 links.

Example: ['https://github.com/clojure/clojure', 'http://clojure.com/', . . .]
"
    (find-res (parse "clojure_google.html"))
  )

(defn -main []
  (println (str "Found " (count (get-links)) " links!"))
)
