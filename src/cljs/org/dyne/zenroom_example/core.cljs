(ns org.dyne.zenroom-example.core
  (:require
   [cljs.reader :refer [read-string]]
   [clojure.string :as string]
   [reagent.core :as r]
   [reagent.dom :as dom]
   [zenroom :default zenroom]))

(defn panel []
  (let [input (r/atom "")
        KEYS (r/atom "")
        DATA (r/atom "")
        success? (r/atom false)
        results (r/atom [])
        evaluate! (fn []
                    (reset! results [])
                    (doto zenroom
                      (.script @input)
                      (.keys (clj->js (read-string @KEYS)))
                      (.data (clj->js (read-string @DATA)))
                      (.print (fn [s] (swap! results conj s)))
                      (.success (fn [] (reset! success? true)))
                      (.error (fn [] (reset! success? false)))
                      .zencode-exec))]
    (fn []
      [:div.container
       [:h1.title.is-1 "Zenroom Demo"]
       [:div.columns
        [:div.column
         [:h2.subtitle.is-2 "Code"
          [:textarea.textarea {:rows 20 :on-change (fn [e] (reset! input (-> e .-target .-value)))}]
          [:button.button {:on-click evaluate!} "Evaluate"]]
         [:label "Compiles? " [:input {:type :checkbox :checked @success? :read-only true}]]]
        [:div.column
         [:h2.subtitle.is-2 "Keys"
          [:textarea.textarea {:rows 3 :on-change (fn [e] (reset! KEYS (-> e .-target .-value)))}]]
         [:h2.subtitle.is-2 "Data"
          [:textarea.textarea {:rows 3 :on-change (fn [e] (reset! DATA (-> e .-target .-value)))}]]
         [:h2.subtitle.is-2 "Result"
          [:textarea.textarea {:rows 10 :value (string/join "\n" @results) :read-only true}]]]]])))

(defn ^:export ^:dev/after-load init []
  (dom/render [panel] (.getElementById js/document "app")))
