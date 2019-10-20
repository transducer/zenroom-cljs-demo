(ns build
  (:require
   [clojure.java.shell :refer [sh]]))

(defn- copy-wasm-to-public
  "Copies zenroom.wasm file to resources/public so that it is available in the
  build.

  Returns a map with `:exit` `:out` and `:err` keys from
  `clojure.java.shell/sh`."
  []
  (sh "cp"
      "node_modules/zenroom/dist/lib/zenroom.wasm"
      "resources/public/"))

(defn- remove-locate-wasm-locally-line
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
  (let [{:keys [exit err]} (copy-wasm-to-public)]
    (if (zero? exit)
      (println "Succesfully copied zenroom.wasm to public")
      (println "Failed to copy zenroom.wasm to public - err: " err)))
  (let [{:keys [exit err]} (remove-locate-wasm-locally-line)]
    (if (zero? exit)
      (println "Succesfully changed zenroom.js's zenroom.wasm path")
      (println "Failed to change zenroom.js's zenroom.wasm path - err: " err)))
  build-state)
