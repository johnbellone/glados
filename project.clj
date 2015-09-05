(defproject com.bloomberg.inf/glados "0.1.0-SNAPSHOT"
  :main glados.core
  :description "FIXME: write description"
  :url "https://github.com/johnbellone/glados"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :bin {:name "glados"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [org.clojure/tools.logging "0.3.1"]
                 [clj-logging-config "1.9.12"]
                 [emiln/slacker "1.4.0"]
                 [environ "1.0.0"]]
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-environ "1.0.0"]
            [lein-bin "0.3.5"]])
