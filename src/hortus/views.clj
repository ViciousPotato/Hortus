(ns hortus.views
  (:use [hiccup core page]
        [hortus.filetype]))

(defn index-page []
  (html5
    [:head
      [:title "Hortus"]]
    [:body
      [:h1 "Hortus"]
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
      [:link {:rel "stylesheet" :media "all" :href "/css/highlighter/xcode.css"}]
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
            [:div.annotation "Nothing"]
            [:div.content 
              [:pre [:code {:class (deduce-file-type path)} (read-file path)]]
            ]
          ]
        ]
      ]
      [:script {:type "text/javascript" :src "/js/highlight.pack.js"}]
      [:script {:type "text/javascript" :src "/js/hortus.js"}]
    ]
  ))