(ns tttback.users+lists-test
  (:use clojure.test
        tttback.test-core)
  (:require [tttback.models.users :as users]
            [tttback.models.lists :as lists]
            [environ.core :refer [env]]))

; Run each test in an isolated db transaction and rollback
; afterwards
(use-fixtures :each with-rollback)

(deftest user-list-interactions
  (let [user (users/create {:name "Jed" :email "jed@i.com"})
        my-list (lists/create {:user_id (user :id)
                               :title "Ways to use the force"})]
    (testing "Get user for given list"
      (is (= (users/find-by-id (user :id))
            (users/for-list my-list))))

    (testing "Get all lists for user"
      (is (= 1 (count (lists/for-user user))))
      (lists/create {:user_id (user :id)
                     :title "Lightsaber wishlist"})
      (is (= 2 (count (lists/for-user user)))))))

(deftest cascading-operations
  (testing "Deleting user removes associated lists"
    (let [jack (users/create {:name "Jack"
                              :email "beanstalkz@example.com"})
          tmp-list (lists/create {:user_id (jack :id)
                                  :title "Talking points"})]
      (users/delete-user jack)
      (is (= nil (lists/find-by-id (tmp-list :id)))))))