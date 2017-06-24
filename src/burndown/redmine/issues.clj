(ns burndown.redmine.issues
  (:require [clj-http.client :as client]
            [environ.core :refer [env]]))

(defn get-issues [limit]
  (client/get (env :issues-url)
              {:query-params {:project_id (env :project-id)
                              :fixed_version_id (env :version-id)
                              :status_id "*"
                              :limit limit}
               :headers {:X-Redmine-API-Key (env :api-key)}
               :insecure? (= (env :insecure) "true")
               :as :json}))

(defn get-all-issues []
  (let [total-count (get-in (get-issues 1) [:body :total_count])]
    (get-in (get-issues total-count) [:body :issues])))

(defn hours-by-done-ratio [issues ratio]
  (->> (filter #(= ratio (:done_ratio %)) issues)
       (map :estimated_hours)
       (remove nil?)
       (reduce +)))

(defn get-times []
  (let [issues (get-all-issues)]
    {:total (reduce + (remove nil? (map :estimated_hours issues)))
     :todo (hours-by-done-ratio issues 0)
     :process (hours-by-done-ratio issues 30)
     :done (hours-by-done-ratio issues 100)}))
