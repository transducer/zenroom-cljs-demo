(ns org.dyne.zenroom-example.core
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as re-frame]
   [org.dyne.zenroom-example.events :as events]
   [org.dyne.zenroom-example.views :as views]
   [org.dyne.zenroom-example.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))