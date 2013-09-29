(ns hortus.handler
  (:use compojure.core
        hortus.views)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (index-page))
  (GET ["/code/:path", :path #".+"] [path] code-page)
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
