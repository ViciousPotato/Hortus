(ns hortus.handler
  (:use compojure.core
        hortus.views
        ring.middleware.session)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hortus.db :as db]))

(defn init []
  (println "Hortus starting...")
  (if-not (.exists (java.io.File. "./resources/db.db"))
    (db/create-tables)))

(defroutes app-routes
  (GET  "/" {session :session} (index-page))
  (GET  ["/code/:md5"] [md5] (code-page md5))
  (POST ["/code"] [filename content] (create-code filename content))
  (POST ["/annotation/:part"] [part] api-annotation)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-session (handler/site app-routes)))
