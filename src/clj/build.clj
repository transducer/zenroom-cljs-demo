(ns build
  (:require
   [clojure.java.shell :refer [sh]]
   [clojure.string :as string]))

(defn- ppstring-fn-name
  "Pretty printed string of name of fn"
  [f]
  (let [[_ name _] (string/split (str f) #"\$|@")]
    (string/replace name "_" " ")))

(defn- call-and-log-sh [sh-fn]
  (let [{:keys [exit err]} (sh-fn)]
    (if (zero? exit)
      (println "Succesfully run" (ppstring-fn-name sh-fn))
      (println "Failed to run " (ppstring-fn-name sh-fn) " - err: " err))))

(defn- copy-wasm-to-public
  "Copies zenroom.wasm file to resources/public so that it is available in the
  build."
  []
  (call-and-log-sh
   #(sh "cp"
        "node_modules/zenroom/dist/lib/zenroom.wasm"
        "resources/public/")))

(defn- remove-locate-wasm-locally-line
  "In the zenroom.js's node_module there's a line that tries to retrieve the wasm
  locally. This function removes that line so that the wasm is retrieved from
  resources/public instead."
  []
  (call-and-log-sh
   #(sh "sed" "-i.bak" "/wasmBinaryFile = locateFile/d"
        "node_modules/zenroom/dist/lib/zenroom.js")))

(defn fix-zenroom-wasm-path-hook
  {:shadow.build/stage :flush}
  [build-state]
  (copy-wasm-to-public)
  (remove-locate-wasm-locally-line)
  build-state)
