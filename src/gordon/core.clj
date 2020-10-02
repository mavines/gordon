(ns gordon.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [ubergraph.core :as uber]
            [ubergraph.alg :as alg]
            [loom.io :as io]
            [loom.alg :as lalg]
            [gordon.data :as data]
            [gordon.track :as track]
            [gordon.graph :as graph]))


(defn- render [*track width height]
  (track/render @*track width height))

(defn- setup [*track]
  (q/frame-rate 30)
  *track)

(defn- update-state [state *track]
  *track)

(defn draw-track [*track width height]
  (q/defsketch gordon
    :title "Train Tracks"
    :size [width height]
    :setup #(setup *track)
    :update #(update-state % *track)
    :draw #(render % width height)
    :features [:keep-on-top]
    :middleware [m/fun-mode]))

(defn track->graph [track]
  (graph/track->graph track))

(defn graph->dot [graph filename]
  (io/dot graph filename))

(defn track->dot [track filename]
  (-> (track->graph track)
      (graph->dot filename)))

(defn viz-track
  ([track] (viz-track track {}))
  ([track opts]
   (-> (track->graph track)
       (uber/viz-graph opts))))
