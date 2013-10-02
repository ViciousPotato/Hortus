(defproject hortus "0.1.0-SNAPSHOT"
  :description "Code annotation tool"
  :url "https://github.com/ViciousPotato/Hortus.git"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [org.python/jython-standalone "2.5.3"]
                 [org.pygments/pygments "1.6"]
                 [org.clojure/java.jdbc "0.3.0-alpha5"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [markdown-clj "0.9.33"]]
  :plugins [[lein-ring "0.8.5"]
            [hiccup-bridge "1.0.0-SNAPSHOT"]]
  :ring {:handler hortus.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
