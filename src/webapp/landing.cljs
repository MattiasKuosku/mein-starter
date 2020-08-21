(ns webapp.landing

  (:require [saikyun.mein.dom :as dom]
            [saikyun.mein.core :refer [render cleanup!]]
            [saikyun.mein.props :as p]))



(defonce app-div (dom/get-or-create "app"))
(def todos (atom ["Köp kaffe" "Köp slatt mjölk"]))

(def view [:div [:h1 "Todos"]
           (-> (into [:div] (for [t @todos] [:div t]))
               (p/watch todos (fn [e _ _ new-todos]
                                (set! (.-innerHTML e) new-todos)
                                (println "Test"))))
           (->
            [:input]
            (p/spice :mein/on {:change  #(swap! todos conj (.. % -target -value))}))])
(println view)
(println (into [1] '("2" "3")))
(defn start
  []
  (render app-div view)
  (println "Starting Jona!"))

(defn init
  []
  (start))

(defn stop
  [] (cleanup! ))
