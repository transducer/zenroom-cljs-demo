(ns org.dyne.zenroom-example.views
  (:require
   [clojure.string :as string]
   [org.dyne.zenroom-example.events :as events]
   [org.dyne.zenroom-example.subs :as subs]
   [re-frame.core :as re-frame]))

(defn main-panel []
  (let [input (atom "")
        results (re-frame/subscribe [::subs/zenroom-results])]
    [:<>
     [:input {:on-change (fn [e] (reset! input (-> e .-target .-value)))}]
     [:button {:on-click #(re-frame/dispatch [::events/script-submitted @input])} "Evaluate"]
     [:textarea {:value (string/join "\n" @results) :read-only true}]]))
