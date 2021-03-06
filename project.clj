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
                 [korma "0.4.0"]
                 [org.postgresql/postgresql "9.2-1002-jdbc4"]
                 [org.clojure/java.jdbc "0.3.7"]
                 [buddy/buddy-hashers "0.4.0"]
                 [buddy/buddy-auth "0.4.0"]
                 [crypto-random "1.2.0"]
                 [ragtime "0.5.2"]
                 [environ "0.4.0"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-ring "0.8.10"]
            ;[ragtime/ragtime.lein "0.3.6"]
            [lein-environ "0.4.0"]]
  :uberjar-name "tttback-standalone.jar"

  :aliases {"migrate"  ["run" "-m" "tttback.migration/migrate"]
            "rollback" ["run" "-m" "tttback.migration/rollback"]}

  :main ^:skip-aot tttback.handler
  :ring {:handler tttback.handler/app
         :nrepl {:start? true
                 :port 9998}}

  ;:ragtime {:migrations ragtime.sql.files/migrations
            ;:database (str (clojure.string/replace (System/getenv "DATABASE_URL") #"postgres://" "jdbc:postgresql://") "?user=viqfzfczwvbocw&password=c-bv0hP8USS5moUYvQ8G8pgqaF")}
            ;:database "jdbc:postgresql://ec2-54-235-78-155.compute-1.amazonaws.com:5432/dfpa2m3fu45c9k?user=viqfzfczwvbocw&password=c-bv0hP8USS5moUYvQ8G8pgqaF"}
            ;:database ~(let [db_url (System/getenv "DATABASE_URL")]
                       ;(str "jdbc:postgresql://" (.substring db_url (inc (.indexOf db_url "@"))) "?user=viqfzfczwvbocw&password=c-bv0hP8USS5moUYvQ8G8pgqaF"))}
            ;:database "jdbc:postgresql://viqfzfczwvbocw:c-bv0hP8USS5moUYvQ8G8pgqaF@ec2-54-235-78-155.compute-1.amazonaws.com:5432/dfpa2m3fu45c9k?user=viqfzfczwvbocw&password=c-bv0hP8USS5moUYvQ8G8pgqaF"}

  :profiles
  {:uberjar {:aot :all}
   :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]
         :env {:tttback-db "dfpa2m3fu45c9k"
               :tttback-db-user "viqfzfczwvbocw"
               :tttback-db-pass "c-bv0hP8USS5moUYvQ8G8pgqaF"
               :tttback-db-host ~(.getHost (java.net.URI. (System/getenv "DATABASE_URL")))}}
;   :test {:ragtime
;          {:database "jdbc:postgresql://localhost:5432/restful_test?user=restful_test&password=pass_test"}
;          :env {:tttback-db "restful_test"
;                :tttback-db-user "restful_test"
;                :tttback-db-pass "pass_test"}}
   :test {:dependencies [[javax.servlet/servlet-api "2.5"]
                         [ring-mock "0.1.5"]]
          :env {:tttback-db "dfpa2m3fu45c9k"
                :tttback-db-user "viqfzfczwvbocw"
                :tttback-db-pass "c-bv0hP8USS5moUYvQ8G8pgqaF"
                :tttback-db-host ~(.getHost (java.net.URI. (System/getenv "DATABASE_URL")))}}
   })
