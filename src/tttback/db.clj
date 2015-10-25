(ns tttback.db
  (:use korma.db)
  (:require [environ.core :refer [env]]))

(defdb db (postgres {:db (get env :tttback-db "restful_dev")
                     :user (get env :tttback-db-user "restful_dev")
                     :password (get env :tttback-db-pass "pass_dev")}))

;:host (get env :tttback-db-host "ec2-54-235-78-155.compute-1.amazonaws.com")
;:port (get env :tttback-db-port 5432)