(ns hortus.handler
  (:use compojure.core
        hortus.views
        ring.middleware.session)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET  "/" {session :session} (index-page))
  (GET  ["/code/:path", :path #".+"] [path] code-page)
  (POST ["/annotation/:part"] [part] api-annotation)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-session (handler/site app-routes)))
