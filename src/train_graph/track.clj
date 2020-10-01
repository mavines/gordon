(ns train-graph.track
  (:require [quil.core :as q]))

(def hex-lines [[[87 0] [174 50]]
                [[174 50] [174 150]]
                [[174 150] [87 200]]
                [[87 200] [0 150]]
                [[0 150] [0 50]]
                [[0 50] [87 0]]])

(def hex-width 175)
(def hex-height 150)
(def hex-center [87 100])
(def link-positions {:nw [40 20]
                     :w [0 100]
                     :ne [135 20]
                     :sw [40 180]
                     :e [175 100]
                     :se [135 180]})

(defn update-x [line row col]
  (let [half-offset (if (odd? row)
                      (/ hex-width 2)
                      0)
        offset (* col hex-width)]
    (-> line
        (update-in [0 0] + offset half-offset)
        (update-in [1 0] + offset half-offset))))

(defn update-y [line row]
  (let [offset (* row hex-height)]
    (-> line
        (update-in [0 1] + offset)
        (update-in [1 1] + offset))))

(defn hex [tile]
  (let [{:keys [row col]} tile]
    (->> hex-lines
         (map #(update-x % row col))
         (map #(update-y % row)))))


(defn link-points [link]
  (let [start (first link)
        end (second link)]
    (vector (get link-positions start)
            hex-center
            (get link-positions end)
            hex-center)))

(defn render-link [row col link]
  )

(defn render-tile [tile]
  (doall (map #(apply q/line %) (hex tile))))

(defn render [track]
  (q/background 255 255 255)
  (q/stroke-weight 5)
  (q/stroke 0 0 0)
  (doall (map render-tile track)))

