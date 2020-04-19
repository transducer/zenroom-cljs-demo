(ns org.dyne.zenroom-example.core
  (:require
   [org.dyne.zenroom-example.config :as config]
   [org.dyne.zenroom-example.events :as events]
   [org.dyne.zenroom-example.views :as views]
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
