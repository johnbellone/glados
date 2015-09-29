(ns glados.core
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure.tools.logging :as log]
            [slacker.client :as client]
            [clojure.tools.cli :refer [parse-opts]]
            [org.httpkit.server :as http]
            [environ.core :refer [env]]
            [glados.handler :as handler]
            [clj-logging-config.log4j :refer [set-logger!]]))

(def cli-options
  [["-p" "--port PORT" "Set the HTTP server port."
    :default (env :port 8080)
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 1024 % 0x10000) "Must be integer between 1024 and 65536."]]
   ["-S" "--slack-token TOKEN" "Sets the Slack API token."]
   ["-l" "--log-level LEVEL" "Sets the log level."
    :default "info"
    :parse-fn keyword
    :validate [#{:trace :debug :info :warn :fatal} "Must be 'trace', 'debug', 'info', 'warn' or 'fatal'."]]
   ["-v" nil "Sets the verbosity level."
    :id :verbosity
    :default 0
    :assoc-fn (fn [m k _] (update-in m [k] inc))]
   ["-h" "--help" "Prints this message."]])

(defn- usage
  [options-summary]
  (->> ["Usage: glados"
        ""
        "Options:"
        options-summary]
       (string/join \newline)))

(defn- error-message
  [errors]
  (str "The following errors occurred during execution:\n\n"
       (string/join \newline errors)))

(defn- exit
  [status message]
  (log/error message)
  (System/exit status))

(defn -main
  [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
    (set-logger! :level (:log-level options))
    (cond
      (:help options) (exit 0 (usage summary))
      errors (exit 1 (error-message errors)))
    (http/run-server handler/app {:port (:port options)})))
