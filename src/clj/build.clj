(ns build
  (:require
   [clojure.java.shell :refer [sh]]))

(defn- copy-wasm-to-public
  "Copies zenroom.wasm file to resources/public so that it is available in the
  build."
  []
  (let [{:keys [exit err]} (sh "cp"
                               "node_modules/zenroom/dist/lib/zenroom.wasm"
                               "resources/public/")]
    (if (zero? exit)
      (println "Copied zenroom.wasm to resources/public")
      (println "Failure in copy zenroom.wasm to resources/public - err: " err))))

(defn- remove-locate-wasm-locally-line
  "In the zenroom.js's node_module there's a line that tries to retrieve the wasm
  locally. This function removes that line so that the wasm is retrieved from
  resources/public instead."
  []
  (let [{:keys [exit err]} (sh "sed" "-i.bak" "/wasmBinaryFile = locateFile/d"
                               "node_modules/zenroom/dist/lib/zenroom.js")]
    (if (zero? exit)
      (println "Changed zenroom.js's zenroom.wasm path")
      (println "Failure in change zenroom.js's zenroom.wasm path - err: " err))))

(defn setup-zenroom-wasm-hook
  "Perform the steps described in https://www.dyne.org/using-zenroom-with-javascript-react-part3/:

  - Copy zenroom.wasm to resources/public
  - Remove a line in zenroom.js glue code that tries to find the wasm file
    locally (in node_modules)"
  {:shadow.build/stage :compile-prepare}
  [build-state]
  (copy-wasm-to-public)
  (remove-locate-wasm-locally-line)
  build-state)
