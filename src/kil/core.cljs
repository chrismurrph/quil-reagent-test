(ns ^:figwheel-always kil.core
  (:require [reagent.core :as reagent :refer [atom]]
            [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

(enable-console-print!)

(def w 400)
(def h 400)

(defn setup-quil-canvas []
  {:t 1})

(defn update-quil-canvas [state]
  (update-in state [:t] inc))

(defn draw-quil-canvas [state]
  (q/background 255)
  (q/fill 0)
  (q/ellipse (rem (:t state) w) 46 55 55))

(q/defsketch quil-sketch-fn
  :setup  setup-quil-canvas
  :update update-quil-canvas
  :draw   draw-quil-canvas
  :host "host-canvas-id"
  :no-start true
  :middleware [m/fun-mode]
  :size [w h])

(defn hello-world []
  (reagent/create-class
    {:reagent-render (fn [] [:canvas#host-canvas-id {:width w :height h}])
     :component-did-mount quil-sketch-fn}))

(reagent/render-component
  [hello-world]
  (. js/document (getElementById "app")))

(defn on-js-reload [])

