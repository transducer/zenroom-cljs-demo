(ns zenroom-example.events
  (:require
   [re-frame.core :as re-frame]
   [zenroom-example.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
