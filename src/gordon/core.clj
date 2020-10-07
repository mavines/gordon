(ns gordon.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [ubergraph.core :as uber]
            [ubergraph.alg :as alg]
            [loom.io :as io]
            [loom.alg :as lalg]
            [gordon.data :as data]
            [gordon.track :as track]
            [gordon.graph :as graph]
            [gordon.tiles :as tiles]))


(defn- render [*track width height]
  (track/render @*track width height))

(defn- setup [*track]
  (q/frame-rate 1)
  *track)

(defn- find-train [track]
  (first (filter :train track)))

(defn- move-train [track]
  (let [train-tile (find-train track)
        entrance (:train train-tile)
        exit (rand-nth (tiles/connected-sides train-tile entrance))
        next-tile (tiles/next-tile-position track train-tile exit)
        opposite-entrance (tiles/opposite-entrance exit)]
    (map #(if (= next-tile %)
            (assoc % :train opposite-entrance)
            (dissoc % :train))
         track)))

(defn- update-state [state]
  (move-train state))

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

(q/defsketch gordon
  :title "Train Tracks"
  :size [800 800]
  :setup #(setup data/double-loop)
  :update #(move-train %)
  :draw #(track/render % 800 800)
  :features [:keep-on-top]
  :middleware [m/fun-mode])
