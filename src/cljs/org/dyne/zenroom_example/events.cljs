(ns org.dyne.zenroom-example.events
  (:require
   [re-frame.core :as re-frame]
   [org.dyne.zenroom-example.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
