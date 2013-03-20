(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

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
    (loop [res [] arr (parse "clojure_google.html")]
      (if (empty? arr)
        res
        (let [el (first arr) tail (next arr)]
          (if (vector? el)
            (if (= (get (get el 1) :class) "r")
              (recur (conj res (get (get (get el 2) 1) :href)) tail)
              (recur res (concat (nthrest el 2) tail)))
            (recur res tail))
        )))
  )

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))
