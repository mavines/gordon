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


(defn- render [track width height]
  (track/render track width height))

(defn- setup [track]
  (q/frame-rate 10)
  track)

(defn- find-train [track]
  (first (filter :train track)))

(defn- move-train [track]
  (let [train-tile (find-train track)
        entrance (:train train-tile)
        track-line (rand-nth (tiles/connected-lines train-tile entrance))
        exit (first (filter #(not= entrance %) track-line))
        next-tile (tiles/next-tile-position track train-tile exit)
        opposite-entrance (tiles/opposite-entrance exit)]
    (->> track
         (map #(dissoc % :train))
         (map #(condp = (:id %)
                 (:id train-tile) (update-in % [:traveled track-line] (fnil inc 0))
                 (:id next-tile) (assoc % :train opposite-entrance)
                 %)))))


(defn- update-state [state]
  (move-train state))

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

#_(q/defsketch moving-train
  :title "Train Tracks"
  :size [800 800]
  :setup #(setup data/double-loop)
  :update #(move-train %)
  :draw #(track/render % 800 800)
  :features [:keep-on-top]
    :middleware [m/fun-mode])

(defn travel [track steps]
  (loop [i 0
         t track]
    (if (>= i steps)
      t
      (recur (inc i) (vec (move-train t))))))

(def traveled (travel data/big-track 50000))


(q/defsketch heat-map
  :title "Train Tracks"
  :size [800 800]
  :setup #(setup data/big-track)
  :update #(move-train %)
  :draw #(track/render % 800 800)
  :features [:keep-on-top]
  :middleware [m/fun-mode])


(q/defsketch just-track
  :title "Train Tracks"
  :size [800 800]
  :setup #(setup traveled)
  :update #(when % traveled)
  :draw #(track/render % 800 800)
  :features [:keep-on-top]
  :middleware [m/fun-mode])
