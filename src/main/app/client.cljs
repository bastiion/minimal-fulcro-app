(ns app.client
  (:require
    ;; internal namespaces
    [app.ui :as ui]
    [app.application :refer [app]]

    ;; external namespaces
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.algorithms.data-targeting :as targeting]
    [com.fulcrologic.fulcro.data-fetch :as df]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Main entry point for JS
(defn ^:export init []
  (app/mount! app ui/Root "app")
  ;; initiate the load action at the app init
  (df/load! app :friends ui/PersonList)
  #_(df/load! app [:person/id 3]  ui/PersonList)              ;; throws not-found
  #_(df/load! app [:person/id 3] ui/Person {:target (targeting/append-to [:list/id :friends :list/people])})
  (df/load! app :enemies ui/PersonList)
  (js/console.log "Loaded"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; These are for shadow-cljs
(defn ^:export refresh []
  ;; re-mounting will cause forced UI refresh
  (app/mount! app ui/Root "app")
  (js/console.log "Hot reload"))