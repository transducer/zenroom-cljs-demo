(ns zenroom-example.views
  (:require
   [re-frame.core :as re-frame]
   [zenroom :default zenroom]
   [zenroom-example.subs :as subs]))

(defn main-panel []
  [:div "hallo"]
  #_(let [script "print(\"hello\")"
        _ (.init zenroom)
        zr (.zenroom_exec (.script zenroom script))
        debug (.__debug zenroom)]
    [:div
     [:div (js/Object.getOwnPropertyNames debug)]
     [:div (.-script debug)]
     [:div (js/Object.getOwnPropertyNames zenroom)]]))
