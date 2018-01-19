(ns message-server.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :refer [response]]
            [clojure.java.jdbc :as j]
            [ring.middleware.json :refer  [wrap-json-response wrap-json-body]]
            [environ.core :refer [env]]))

(defn test-query [db-map]
  (j/query db-map ["SELECT * FROM test;"]))

(defn app-routes [db-map]
  (routes
   (GET "/hello" [] "Hello World")
   (GET "/test" [] (response (test-query db-map)))
   (route/not-found "Not Found")))

(defn make-app [db-map]
  (-> db-map
      (app-routes)
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete])
      (wrap-json-response)
      (wrap-json-body {:keywords? true})
      (wrap-defaults api-defaults)))


(def db-map {:subprotocol "mysql"
             :subname (env :healthworkers-msg-db-url)
             :user (env :healthworkers-msg-db-user)
             :password (env :healthworkers-msg-db-password)})

(def app (make-app db-map))
