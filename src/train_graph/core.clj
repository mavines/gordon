(ns train-graph.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [ubergraph.core :as uber]
            [ubergraph.alg :as alg]
            [loom.io :as io]
            [loom.alg :as lalg]
            [train-graph.data :as data]
            [train-graph.track :as track]
            [train-graph.graph :as graph]))


(defn render [track width height]
  (track/render track width height))

(defn setup []
  ; Set frame rate to 30 frames per second.
  (q/frame-rate 30)
  ; setup function returns initial state. It contains
  ; circle color and position.
  data/stuck-track)

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  data/stuck-track)


(q/defsketch train-graph
  :title "Train Tracks"
  :size [1000 800]
  ; setup function called only once, during sketch initialization.
  :setup setup
  ; update-state is called on each iteration before draw-state.
  :update update-state
  :draw #(render % 1000 800)
  :features [:keep-on-top]
  ; This sketch uses functional-mode middleware.
  ; Check quil wiki for more info about middlewares and particularly
  ; fun-mode.
  :middleware [m/fun-mode])

(def stuck (graph/track->graph data/stuck-track))
(def loop (graph/track->graph data/double-loop-track))

(uber/pprint loop)
(alg/pprint-path (alg/shortest-path loop {:start-node :hd
                                           :end-node :eh}))
(alg/strongly-connected? loop)
(alg/connected? loop)
(lalg/scc loop)
(lalg/scc stuck)
(io/dot stuck "stuck-train.dot")
