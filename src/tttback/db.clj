(ns tttback.db
  (:require [korma.db :as korma])
  (:require [environ.core :refer [env]]
            [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]))

(defdb db (korma.postgres {:db (get env :tttback-db "restful_dev")
                     :user (get env :tttback-db-user "restful_dev")
                     :password (get env :tttback-db-pass "pass_dev")
                     :host (get env :tttback-db-host "localhost")
                     :port (get env :tttback-db-port 5432)}))

(defn load-config []
  {:datastore  (jdbc/sql-database (System/getenv "DATABASE_URL"))
   :migrations (jdbc/load-resources "migrations")})

(defn migrate []
  (repl/migrate (load-config)))

(defn rollback []
  (repl/rollback (load-config)))