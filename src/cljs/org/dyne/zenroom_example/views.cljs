(ns org.dyne.zenroom-example.views
  (:require
   [re-frame.core :as re-frame]
   [zenroom :default zenroom]
   [org.dyne.zenroom-example.subs :as subs]))

(defn main-panel []
  (let [script "print(\"hello\")"
        zr (.zenroom_exec (.script zenroom script))
        debug (.__debug zenroom)]
    [:<>
     [:div "hallo"]
     [:div (js/Object.getOwnPropertyNames debug)]
     [:div (.-script debug)]
     [:div (js/Object.getOwnPropertyNames zenroom)]]))
