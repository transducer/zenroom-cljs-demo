(ns view
  (:require
   [cljs.reader :refer [read-string]]
   [clojure.string :as string]
   [reagent.core :as r]
   [reagent.dom :as dom]
   [zenroom]))

(defonce app-state
  (r/atom
   {:input ""
    :keys ""
    :data ""
    :success? false
    :results []}))

(defn evaluate! []
  (doto zenroom
    (.script (:input @app-state))
    (.keys (clj->js (read-string (:keys @app-state))))
    (.data (clj->js (read-string (:data @app-state))))
    (.print (fn [s] (swap! app-state update :results conj s)))
    (.success (fn [] (swap! app-state assoc :success? true)))
    (.error (fn [] (swap! app-state assoc :success? false)))
    .zencode-exec))

(defn clear! []
  (swap! app-state assoc :results []))

(defn zencode-column []
  [:div.column
   [:div.subtitle.is-2 "Zencode"]
   [:textarea.textarea
    {:rows 20
     :on-change (fn [e] (swap! app-state assoc :input (-> e .-target .-value)))}]
   [:button.button {:on-click (fn [] (clear!) (evaluate!))} "Evaluate"]
   [:label.label.is-pulled-right "Compiles? "
    [:input.checkbox
     {:type
      :checkbox
      :checked (:success? @app-state)
      :read-only true}]]])

(defn keys-data-results-column []
  [:div.column
   [:h2.subtitle.is-2 "Keys"]
   [:textarea.textarea
    {:rows 3
     :on-change (fn [e] (swap! app-state assoc :keys (-> e .-target .-value)))}]
   [:h2.subtitle.is-2 "Data"]
   [:textarea.textarea
    {:rows 3
     :on-change (fn [e] (swap! app-state assoc :data (-> e .-target .-value)))}]
   [:h2.subtitle.is-2 "Output"]
   [:textarea.textarea.has-background-dark.has-text-success
    {:rows 10 :value (string/join "\n" (:results @app-state)) :read-only true}]])

(defn panel []
  [:div.container
   [:h1.title.is-1 "Zenroom Demo"]
   [:div.columns
    [zencode-column]
    [keys-data-results-column]]])

(defn ^:export ^:dev/after-load init []
  (dom/render [panel] (.getElementById js/document "app")))
