(ns org.dyne.zenroom-example.views
  (:require
   [clojure.string :as string]
   [org.dyne.zenroom-example.events :as events]
   [org.dyne.zenroom-example.subs :as subs]
   [re-frame.core :as re-frame]))

(defn main-panel []
  (let [input (atom "")
        success? (re-frame/subscribe [::subs/zenroom-success?])
        results (re-frame/subscribe [::subs/zenroom-results])]
    [:<>
     [:textarea {:on-change (fn [e] (reset! input (-> e .-target .-value)))}]
     [:button {:on-click #(re-frame/dispatch [::events/script-submitted @input])} "Evaluate"]
     [:textarea {:value (string/join "\n" @results) :read-only true}]
     [:div "Compiles?"]
     [:div {:style {:background-color (if @success? "green" "red") :height "30px" :width "30px"}}]]))
