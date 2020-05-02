(ns org.dyne.zenroom-example.core
  (:require
   [cljs.reader :refer [read-string]]
   [clojure.string :as string]
   [reagent.core :as r]
   [reagent.dom :as dom]
   [zenroom]))

(defonce db
  (r/atom
   {:input ""
    :keys ""
    :data ""
    :success? false
    :results []}))

(defn evaluate! []
  (doto zenroom
    (.script (:input @db))
    (.keys (clj->js (read-string (:keys @db))))
    (.data (clj->js (read-string (:data @db))))
    (.print (fn [s] (swap! db update :results conj s)))
    (.success (fn [] (swap! db assoc :success? true)))
    (.error (fn [] (swap! db assoc :success? false)))
    .zencode-exec))

(defn clear! []
  (swap! db assoc :results []))

(defn panel []
  [:div.container
   [:h1.title.is-1 "Zenroom Demo"]
   [:div.columns
    [:div.column
     [:div.subtitle.is-2 "Zencode"]
     [:textarea.textarea
      {:rows 20
       :on-change (fn [e] (swap! db assoc :input (-> e .-target .-value)))}]
     [:button.button {:on-click (fn [] (clear!) (evaluate!))} "Evaluate"]
     [:label.label.is-pulled-right "Compiles? "
      [:input.checkbox {:type :checkbox :checked (:success? @db) :read-only true}]]]
    [:div.column
     [:h2.subtitle.is-2 "Keys"]
     [:textarea.textarea {:rows 3 :on-change (fn [e] (swap! db :keys (-> e .-target .-value)))}]
     [:h2.subtitle.is-2 "Data"]
     [:textarea.textarea {:rows 3 :on-change (fn [e] (swap! db :data (-> e .-target .-value)))}]
     [:h2.subtitle.is-2 "Output"]
     [:textarea.textarea.has-background-dark.has-text-success
      {:rows 10 :value (string/join "\n" (:results @db)) :read-only true}]]]])

(defn ^:export ^:dev/after-load init []
  (dom/render [panel] (.getElementById js/document "app")))
