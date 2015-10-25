(ns tttback.migration
  (:require [ragtime.jdbc :as jdbc]
            [ragtime.repl :as repl]))

(defn load-config []
  {:datastore  (jdbc/sql-database (System/getenv "DATABASE_URL"))
   :migrations (jdbc/load-resources "migrations")})

(defn migrate []
  (repl/migrate (load-config)))

(defn rollback []
  (repl/rollback (load-config)))