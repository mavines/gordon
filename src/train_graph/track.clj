(ns trains.track)

(defonce base-hex [[87 0] [174 50]
                   [174 150] [87 200]
                   [0 150] [0 50]
                   [87 0]])

(def hex-width 175)
(def hex-height 150)
(def hex-center [87 100])
(def link-positions {0 [40 20]
                     1 [0 100]
                     2 [135 20]
                     3 [40 180]
                     4 [175 100]
                     5 [135 180]})

(defn update-x [point row col]
  (let [x (first point)        
        half-offset (if (odd? row)
                      (/ hex-width 2)
                      0)
        offset (* col hex-width)]
    (update point 0 #(+ x offset half-offset))))

(defn update-y [point row]
  (let [y (last point)
        offset (* row hex-height)]
    (update point 1 #(+ y offset))))

(defn hex [tile]
  (let [{:keys [row col]} tile]
    (->> base-hex
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
  ^{:key link}
  [:polyline {:points (->> (link-points link)
                           (map #(update-x % row col))
                           (map #(update-y % row)))
              :stroke "red" :stroke-width "5pt"}])

(defn render-tile [tile]
  ^{:key (:id tile)}
  [:g
   [:polyline {:points (hex tile)
               :fill "green" :stroke "black" :stroke-width "5pt"}]
   (map #(render-link (:row tile) (:col tile) %) (:links tile))])

(defn render [track]
  [:div 
   [:svg {:width 300 :height 300 :viewBox [0 0 500 600]}
    (map render-tile track)]])
