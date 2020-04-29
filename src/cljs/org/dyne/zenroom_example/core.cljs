(ns org.dyne.zenroom-example.core
  (:require
   [clojure.string :as string]
   [reagent.core :as r]
   [reagent.dom :as dom]
   [zenroom :default zenroom]))

(defn main-panel []
  (let [input (r/atom "")
        KEYS (r/atom "")
        DATA (r/atom "")
        success? (r/atom true)
        results (r/atom [])
        evaluate! (fn []
                    (doto zenroom
                      (.script @input)
                      (.keys @KEYS)
                      (.data @DATA)
                      (.print (fn [s] (swap! results conj s)))
                      (.success (fn [] (reset! success? true)))
                      (.error (fn [] (reset! success? false)))
                      .zencode-exec))]
    (fn []
      [:<>
       [:textarea {:on-change (fn [e] (reset! input (-> e .-target .-value)))}]
       [:button {:on-click evaluate!} "Evaluate"]
       [:textarea {:value (string/join "\n" @results) :read-only true}]
       [:textarea {:on-change (fn [e] (reset! KEYS (-> e .-target .-value)))}]
       [:textarea {:on-change (fn [e] (reset! DATA (-> e .-target .-value)))}]
       [:div "Compiles?"]
       [:div {:style {:background-color (if @success? "green" "red") :height "30px" :width "30px"}}]])))

(defn ^:export ^:dev/after-load init []
  (dom/render [main-panel] (.getElementById js/document "app")))
