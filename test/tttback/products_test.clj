(ns tttback.products-test
  (:use clojure.test
        tttback.test-core)
  (:require [tttback.models.products :as products]))

(use-fixtures :each with-rollback)

(deftest create-product
  (testing "Create a product increments product count"
    (let [count-orig (products/count-products)]
      (products/create {:title "Cherry Tomatos"
                        :description "Tasty red tomatos"})
      (is (= (inc count-orig) (products/count-products))))))