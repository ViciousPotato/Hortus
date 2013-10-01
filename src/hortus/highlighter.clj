(ns hortus.highlighter
  (:use clojure.string))

(def pygment-code
  (join "\n" [
    "from pygments import highlight"
    "from pygments.formatters import HtmlFormatter"
    "from pygments.lexers import guess_lexer"
    "lexer = guess_lexer(code)"
    "result = highlight(code, lexer, HtmlFormatter())"
    ]))

(defn highlight-code [code]
  (let [python (org.python.util.PythonInterpreter.)]
    (.set  python "code" code)
    (.exec python pygment-code)
    (.get  python "result" String)))