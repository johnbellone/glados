(defproject com.bloomberg.inf/glados "0.1.0-SNAPSHOT"
  :main glados.core
  :description "The neighboorhood Slack bot written in Clojure."
  :url "https://github.com/johnbellone/glados"
  :license {:name "Apache 2.0"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [org.clojure/tools.logging "0.3.1"]
                 [clj-logging-config "1.9.12"]
                 [clj-time "0.11.0"]
                 [emiln/slacker "1.4.0"]
                 [environ "1.0.0"]
                 [metosin/compojure-api "0.23.1"]
                 [http-kit "2.1.19"]
                 [ragtime "0.5.2"]]
  :ring {:handler glados.handler/app}
  :bin {:name "glados"}
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[javax.servlet/servlet-api "2.5"]]
                   :plugins [[lein-bin "0.3.5"]
                             [lein-environ "1.0.0"]
                             [lein-ring "0.9.6"]]}})
