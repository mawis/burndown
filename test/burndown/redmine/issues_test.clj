(ns burndown.redmine.issues-test
  (:require [clojure.test :refer :all]
            [burndown.redmine.issues :refer :all]))

(def issues
  [{:estimated_hours 2.0 :done_ratio 0}
   {:estimated_hours 1.0 :done_ratio 30}
   {:estimated_hours 0.5 :done_ratio 0}
   {:estimated_hours 4.0 :done_ratio 100}
   {:estimated_hours 8.0 :done_ratio 30}
   {:estimated_hours nil :done_ratio 0}
   {:estimated_hours 8.0 :done_ratio 0}
   {:estimated_hours 1.0 :done_ratio 30}
   {:estimated_hours 0.5 :done_ratio 0}
   {:estimated_hours 4.0 :done_ratio 100}
   {:estimated_hours 8.0 :done_ratio 30}
   {:estimated_hours 4.0 :done_ratio 0}
   {:estimated_hours 8.0 :done_ratio 0}
   {:estimated_hours 1.0 :done_ratio 30}
   {:estimated_hours nil :done_ratio 0}
   {:estimated_hours nil :done_ratio 100}
   {:estimated_hours 8.0 :done_ratio 30}
   {:estimated_hours 4.0 :done_ratio 0}
   {:estimated_hours 8.0 :done_ratio 0}
   {:estimated_hours 1.0 :done_ratio 30}
   {:estimated_hours 0.5 :done_ratio 0}
   {:estimated_hours 4.0 :done_ratio 100}
   {:estimated_hours 8.0 :done_ratio 30}
   {:estimated_hours 4.0 :done_ratio 0}
   {:estimated_hours 8.0 :done_ratio 0}
   {:estimated_hours 1.0 :done_ratio 30}
   {:estimated_hours 0.5 :done_ratio 0}
   {:estimated_hours 4.0 :done_ratio 100}
   {:estimated_hours 8.0 :done_ratio 30}
   {:estimated_hours 4.0 :done_ratio 0}
   {:estimated_hours 8.0 :done_ratio 0}
   {:estimated_hours 1.0 :done_ratio 30}
   {:estimated_hours 0.5 :done_ratio 0}
   {:estimated_hours 4.0 :done_ratio 100}
   {:estimated_hours 8.0 :done_ratio 30}
   {:estimated_hours 4.0 :done_ratio 0}
   {:estimated_hours 8.0 :done_ratio 0}
   {:estimated_hours 1.0 :done_ratio 30}
   {:estimated_hours 1.0 :done_ratio 0}
   {:estimated_hours 4.0 :done_ratio 100}
   {:estimated_hours 8.0 :done_ratio 30}
   {:estimated_hours 4.0 :done_ratio 0}
   {:estimated_hours 8.0 :done_ratio 0}])

(defn get-issues-mock [limit]
  (let [limit' (min 42 limit)]
    {:body {:issues (take limit' issues)
            :total_count 42
            :limit limit}}))

(deftest issues-fetching
  (testing "Fetch issues"
    (with-redefs-fn {#'burndown.redmine.issues/get-issues get-issues-mock}
      #(is (= (count (get-all-issues)) 42)))))

(deftest issue-processing
  (testing "Time calculation"
    (with-redefs-fn {#'burndown.redmine.issues/get-issues get-issues-mock}
      #(is (= 164.5 (:total (get-times)))))
    (with-redefs-fn {#'burndown.redmine.issues/get-issues get-issues-mock}
      #(is (= 77.5 (:todo (get-times)))))
    (with-redefs-fn {#'burndown.redmine.issues/get-issues get-issues-mock}
      #(is (= 63.0 (:process (get-times)))))
    (with-redefs-fn {#'burndown.redmine.issues/get-issues get-issues-mock}
      #(is (= 24.0 (:done (get-times)))))))
