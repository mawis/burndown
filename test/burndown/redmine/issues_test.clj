(ns burndown.redmine.issues-test
  (:require [clojure.test :refer :all]
            [burndown.redmine.issues :refer :all]))

(defn get-issues-mock [limit]
  (let [limit' (min 42 limit)]
    {:body {:issues (take limit' (repeat {}))
            :total_count 42
            :limit limit}}))

(deftest issues-fetching
  (testing "Fetch issues"
    (with-redefs-fn {#'burndown.redmine.issues/get-issues get-issues-mock}
      #(is (= (count (get-all-issues)) 42)))))
