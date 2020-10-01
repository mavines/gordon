(ns train-graph.graph
  (:require [quil.core :as q]
            [ubergraph.core :as uber]
            [train-graph.data :as data]
            [train-graph.tiles :as tiles]
            [clojure.pprint :refer [pprint]]))
(def graph1
  (uber/digraph [:a :b] [:a :c] [:b :d] [:b :d]))
(uber/pprint graph1)

(defn neighbors [track tile]
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
      (tiles/south-east? tile other-tile) (tiles/south-east-link? links other-links)
      :else false)))

(defn connected-tile [track tile direction]
  (filter #(tiles/in-direction? tile direction %) track))

(defn connected-neighbors [track tile]
  (filter #(connected-neighbor? tile %) track))

(defn connected-second-neighbors [track tile neighbor]
  (let [next-neighbor-sides (tiles/find-next-link-sides tile neighbor)]
     (mapcat #(connected-tile track neighbor %) next-neighbor-sides)))

(defn tile-by-id [track id]
  (first (filter #(= id (:id %)) track)))

(defn make-second-nodes [kv]
  (let [n1 (key kv)
        others (val kv)]
    (map #(str n1 %) others)))

(defn make-first-node [node neighbor]
  (str node neighbor))

(defn make-all-nodes [node two-steps]
  (let [first-node (str node (-> two-steps key))
        second-nodes (make-second-nodes two-steps)]
    (map #(list first-node %) second-nodes)))

(defn tile->string [tile]
  (:id tile))

(defn steps->ids [steps]
  (reduce-kv (fn [m k v]
               (let [str-key (tile->string k)
                     str-values (map tile->string v)]
                 (assoc m str-key str-values))) {} steps))

(defn make-two-steps [track tile connected-neighbors]
  (apply assoc {}
         (mapcat #(list % (connected-second-neighbors track tile %))
                 connected-neighbors)))

(defn tile->nodes [track tile]
  (when-let [connected (not-empty (connected-neighbors track tile))]
    (let [two-steps (make-two-steps track tile connected)
          tile-string (tile->string tile)
          two-steps-strings (steps->ids two-steps)]
      (mapcat #(make-all-nodes tile-string %) two-steps-strings))))

(defn track->graph [track]
  (remove nil? (mapcat #(tile->nodes track %) track)))

(pprint (track->graph data/double-loop-track))

(defn render [track]
  (let [e (first (filter #(= "e" (:id %)) track))
        c (first (filter #(= "c" (:id %)) track))
        b (first (filter #(= "b" (:id %)) track))
        h (first (filter #(= "h" (:id %)) track))]
    (q/text-size 20)
    (q/fill 0 0 0)
    ;(q/text (str (find-tile 0 0 :e track)) 0 550)
    (q/text (str "B - C: " (connected-neighbor? b c)) 0 550)
    (q/text (str "E - B: " (connected-neighbor? e b)) 0 570)))

(defn track->graph [track]
  track)
