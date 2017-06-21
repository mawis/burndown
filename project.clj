(defproject burndown "0.1.0-SNAPSHOT"
  :description "Burndown formatter"
  :url "https://matthias.wimmer.name/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[cheshire "5.7.1"]
                 [clj-http "3.6.1"]
                 [environ "1.1.0"]
                 [org.clojure/clojure "1.8.0"]
                 [org.postgresql/postgresql "42.0.0"]
                 [ragtime "0.7.1"]
                 [yesql "0.5.3"]]
  :plugins [[lein-environ "1.1.0"]])
