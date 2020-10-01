(ns train-graph.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [train-graph.data :as data]
            [train-graph.track :as track]
            [train-graph.graph :as graph]))


(defn render [track]
  (track/render track)
  (graph/render track))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; setup function returns initial state. It contains
  ; circle color and position.
  data/double-loop-track)

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.1)})


(q/defsketch train-graph
  :title "Train Tracks"
  :size [700 600]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  ;:update update-state
  :draw render
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])
