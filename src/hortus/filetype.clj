(ns hortus.filetype
  (:use [clojure.java.io]))

(defn get-file-extension [file]
  (.substring file (.lastIndexOf file ".")))

(defn deduce-file-type [file]
  (let [mapping {".c" "c" ".asm" "asm"}]
    (val (find mapping (get-file-extension file)))))