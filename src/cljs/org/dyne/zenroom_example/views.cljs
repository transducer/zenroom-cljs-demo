(ns org.dyne.zenroom-example.views
  (:require
   [re-frame.core :as re-frame]
   [zenroom :default zenroom]
   [org.dyne.zenroom-example.subs :as subs]))

(defn property-names [obj]
  (->> obj
       js/Object.getOwnPropertyNames
       js->clj
       (interpose ", ")
       (apply str)))

(defn main-panel []
  (let [script "print(\"hello from zenroom\")"
        zr (.zenroom_exec (.script (.print zenroom (fn [s] (println "hallo" s))) script))
        debug (.__debug zenroom)]
    [:<>
     [:div (property-names debug)]
     [:div (.-script debug)]
     [:div (property-names zenroom)]]))
