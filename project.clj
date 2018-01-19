(defproject message-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.logging "0.4.0"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [compojure "1.6.0"]
                 [ring/ring-defaults "0.3.1"]
                 [environ "1.1.0"]
                 [ring-cors "0.1.11"]
                 [ring/ring-json "0.4.0"]
                 [org.clojure/java.jdbc "0.7.5"]
                 [mysql/mysql-connector-java "5.1.45"]
                 [cheshire "5.8.0"]]
  :plugins [[lein-ring "0.12.3"]]
  :ring {
         :handler message-server.handler/app
         :auto-reload? true
         :auto-refresh true
         }
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
