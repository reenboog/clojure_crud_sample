(defproject tttback "0.1.0-SNAPSHOT"
  :description "Backend for my TopTal project."
  :url "http://github.com/reenboog"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.2.1"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [compojure "1.1.6"]
                 [cheshire "5.3.1"]
                 [ring/ring-json "0.2.0"]
                 [korma "0.3.0-RC5"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [ragtime "0.3.4"]
                 [environ "0.4.0"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.8.10"]
            [ragtime/ragtime.lein "0.3.6"]
            [lein-environ "0.4.0"]]
  :uberjar-name "tttback-standalone.jar"

  :main ^:skip-aot tttback.handler
  :ring {:handler tttback.handler/app
         :nrepl {:start? true
                 :port 9998}}

  :ragtime {:migrations ragtime.sql.files/migrations
            :database ~(str (clojure.string/replace (System/getenv "DATABASE_URL") #"postgres://" "jdbc:postgresql://") "?user=viqfzfczwvbocw&password=bv0hP8USS5moUYvQ8G8pgqaF")}

  :profiles
  {:uberjar {:aot :all}
   :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]
         :env {:tttback-db "dfpa2m3fu45c9k"
               :tttback-db-user "viqfzfczwvbocw"
               :tttback-db-pass "c-bv0hP8USS5moUYvQ8G8pgqaF"}}
   :test {:ragtime
          {:database "jdbc:postgresql://localhost:5432/restful_test?user=restful_test&password=pass_test"}
          :env {:tttback-db "restful_test"
                :tttback-db-user "restful_test"
                :tttback-db-pass "pass_test"}}})
