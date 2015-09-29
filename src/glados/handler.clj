(ns glados.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [schema.core :as s]))

(defapi app
  {:formats [:json-kw]}
  (swagger-ui)
  (swagger-docs
   {:info {:title "GlaDOS"}})
  (context* "/api" []
           (GET* "/webhooks" [] (ok))
           (POST* "/webhooks" [] (ok))
           (PATCH* "/webhooks/:id" [id] (ok))
           (DELETE* "/webhooks/:id" [id] (ok)))
  (GET* "/_status" []
        {:no-doc true}
        (ok)))
