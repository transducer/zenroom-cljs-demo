(ns org.dyne.zenroom-example.subs
  (:require
   [org.dyne.zenroom-example.db :as db]
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::zenroom-results
 (fn [db]
   (println ::results {:db db})
   (::db/zenroom-results db)))

(re-frame/reg-sub
 ::zenroom-success?
 (fn [db]
   (::db/zenroom-success? db)))
