(ns build
  (:require
   [clojure.java.shell :refer [sh]]))

(defn remove-locate-wasm-locally-line
  "In the zenroom.js's node_module there's a line that tries to retrieve the wasm
  locally. This function removes that line so that the wasm is retrieved from
  resources/public instead.

  Returns a map with `:exit` `:out` and `:err` keys from
  `clojure.java.shell/sh`."
  []
  (sh "sed" "-i.bak" "/wasmBinaryFile = locateFile/d"
      "node_modules/zenroom/dist/lib/zenroom.js"))

(defn fix-zenroom-wasm-path-hook
  {:shadow.build/stage :flush}
  [build-state]
  (let [{:keys [exit err]} (remove-locate-wasm-locally-line)]
    (if (zero? exit)
      (println "Succesfully changed zenroom.js's zenroom.wasm path")
      (println "Failed to change zenroom.js's zenroom.wasm path - err: " err)))
  build-state)
