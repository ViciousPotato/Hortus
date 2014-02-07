(ns hortus.db
  (:use [clojure.java.jdbc :exclude (resultset-seq)]))

(def db
  {:classname    "org.sqlite.JDBC"
   :subprotocol  "sqlite"
   :subname      "resources/db.db"
  })

(defn create-annotation-table []
  (try (with-connection db
    (create-table :annotations
      [:id      "INTEGER PRIMARY KEY AUTOINCREMENT"]
      [:code_id :int]
      [:lines   :text]
      [:content :text]))
  (catch Exception e (println e))))

(defn create-code-table []
  (try (with-connection db
    (create-table :code
      [:id       "INTEGER PRIMARY KEY AUTOINCREMENT"]
      [:filename :text]
      [:md5      :text]
      [:content  :text]))))

(defn create-tables []
  (create-code-table)
  (create-annotation-table))

(defn table-exists [table]
  (with-connection db
    (with-query-results res ["SELECT count(*) as cnt FROM sqlite_master WHERE type='table' AND name=?" table]
      (> (:cnt (first (doall res))) 0))))

(defn insert-annotation [annotation]
  (with-connection db
    (insert-records :annotations annotation)))

(defn insert-code [code]
  (with-connection db
    (insert-records :code code)))

(defn code-exist [md5]
  (with-connection db
    (with-query-results res ["SELECT count(*) as cnt FROM code WHERE md5=?" md5]
      (> (:cnt (first (doall res))) 0))))

(defn get-code-by-md5 [md5]
  (with-connection db
    (with-query-results res ["SELECT * FROM code WHERE md5=?" md5]
      (first res))))