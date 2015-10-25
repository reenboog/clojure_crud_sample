(ns tttback.models.users
  (:use korma.core)
  (:require [tttback.entities :as e]
            [buddy.hashers :as hashers]
            [clojure.set :refer [map-invert]]))

(def user-levels
  {"user" ::user
   "manager" ::manager
   "admin" ::admin})

;(derive ::manager ::user)
;(derive ::admin ::manager)

(defn- with-kw-level [user]
  (assoc user :level
    (get user-levels (:level user) ::user)))

(defn find-all []
  (select e/users))

(defn find-by [field value]
  (first
    (select e/users
      (where {field value})
      (limit 1))))

(defn find-by-id [id]
  (find-by :id id))

(defn for-list [listdata]
  (find-by-id (listdata :user_id)))

(defn find-by-email [email]
  (find-by :email email))

(defn create [user]
  (insert e/users
    (values user)))

(defn update-user [user]
  (update e/users
    (set-fields (dissoc user :id))
    (where {:id (user :id)})))

(defn count-users []
  (let [agg (select e/users)]
    (count agg)))

(defn delete-user [user]
  (delete e/users
    (where {:id (user :id)})))