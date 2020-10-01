(ns train-graph.graph
  (:require [quil.core :as q]
            [train-graph.tiles :as tiles]))

(defn neighbors [tile track]
  (->> track
       (filter #(tiles/neighbor? tile %))
       (map :id)))

(defn connected-neighbor? [tile other-tile]
  (let [links (:links tile)
        other-links (:links other-tile)]
    (cond 
      (tiles/north-west? tile other-tile) (tiles/north-west-link? links other-links)
      (tiles/north-east? tile other-tile) (tiles/north-east-link? links other-links)
      (tiles/west? tile other-tile) (tiles/west-link? links other-links)
      (tiles/east? tile other-tile) (tiles/east-link? links other-links)
      (tiles/south-west? tile other-tile) (tiles/south-west-link? links other-links)
      (tiles/south-east? tile other-tile) (tiles/south-east-link? links other-links))))

(defn track->graph [track]
  track)

(defn render [track]
  (let [e (first (filter #(= "e" (:id %)) track))
        c (first (filter #(= "c" (:id %)) track))
        b (first (filter #(= "b" (:id %)) track))
        h (first (filter #(= "h" (:id %)) track))]
    (q/text-size 20)
    (q/fill 0 0 0)
    (q/text (str (connected-neighbor? c e)) 0 550)))
