(ns hortus.views
  (:use [hiccup core page]
        hortus.db
        hortus.filetype
        hortus.highlighter
        clojure.java.io
        markdown.core
        ))

(defn index-page []
  (html5
    [:head
      [:title "Hortus"]
      [:link {:rel "stylesheet" :media "all" :href "/css/docco.css"}]
      [:link {:rel "stylesheet" :media "all" :href "/css/style.css"}]]
    [:body
      [:div#holder]
      [:p#status "Drop your file"]
      [:script {:type "text/javascript" :src "/js/dragdrop.js"}]
    ]))

(defn read-file [file]
  (with-open [rdr (reader file)]
    (slurp rdr)))

(defn code-page [{{path :path} :params}]
  (html5
    [:head
      [:title (str "Hortus" "-" path)]
      [:meta {:http-equiv "content-type" :content "text/html; charset=UTF-8"}]
      [:link {:rel "stylesheet" :media "all" :href "/css/docco.css"}]
      [:link {:rel "stylesheet" :media "all" :href "/css/pygments_style.css"}]
    ]
    [:body
      [:div#container
        [:div#background]
        [:ul#jump_to
          [:li
            [:a.large {:href "javascript:void(0);"} "Jump to &hellip;"]
          ]
        ]
        [:ul.sections
          [:li#title [:div.annotation [:h1 "Code"]]]
          [:li#section-1 
            [:div.annotation "Click to edit"]
            [:div.content.syntax
              (highlight-code (read-file path)) 
              ;[:pre [:code {:class (deduce-file-type path)} (read-file path)]]
            ]
          ]
        ]
      ]
      [:script {:type "text/javascript" :src "/js/jquery-1.10.2.min.js"}]
      [:script {:type "text/javascript" :src "/js/jquery.jeditable.mini.js"}]
      [:script {:type "text/javascript" :src "/js/hortus.js"}]
    ]
  ))

;;/annotation/:part
(defn api-annotation [ctx]
  (md-to-html-string (val (find (:form-params ctx) "value"))))