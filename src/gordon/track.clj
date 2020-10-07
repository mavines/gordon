(ns gordon.track
  (:require [quil.core :as q]))

(def hex-lines [[[87 0] [174 50]]
                [[174 50] [174 150]]
                [[174 150] [87 200]]
                [[87 200] [0 150]]
                [[0 150] [0 50]]
                [[0 50] [87 0]]])

(def base-col-width 175)
(def base-row-height 150)
(def hex-center [87 100])
(def link-positions {:nw [40 25]
                     :w [0 100]
                     :ne [130 25]
                     :sw [40 175]
                     :e [175 100]
                     :se [130 175]})

(defn update-x-point [point row col]
  (let [half-offset (if (odd? row)
                      (/ base-col-width 2)
                      0)
        offset (* col base-col-width)]
    (update point 0 + offset half-offset)))

(defn update-y-point [point row]
  (let [offset (* row base-row-height)]
    (update point 1 + offset)))

(defn update-x [line row col]
  (let [half-offset (if (odd? row)
                      (/ base-col-width 2)
                      0)
        offset (* col base-col-width)]
    (-> line
        (update-in [0 0] + offset half-offset)
        (update-in [1 0] + offset half-offset))))

(defn update-y [line row]
  (let [offset (* row base-row-height)]
    (-> line
        (update-in [0 1] + offset)
        (update-in [1 1] + offset))))

(defn hex [row col]
  (->> hex-lines
       (map #(update-x % row col))
       (map #(update-y % row))))

(defn link-points [link]
  (let [start (first link)
        end (second link)]
    (vector (get link-positions start)
            (get link-positions end))))

(defn link-line [row col link]
  (let [line (link-points link)]
    (-> line
        (update-x row col)
        (update-y row))))



(defn scale-point [width-sf height-sf point]
  (-> point
      (update 0 * width-sf)
      (update 1 * height-sf)))

(defn scale-line [width-sf height-sf line]
  (let [scale-x #(* width-sf %)
        scale-y #(* height-sf %)]
    (map #(vec [(scale-x (first %))
                (scale-y (second %))]) line)))

(defn scale [width-sf height-sf lines]
  (map #(scale-line width-sf height-sf %) lines))

(defn draw-train [row col train-location width-sf height-sf]
  (let [from-point (-> (train-location link-positions)
                       (update-x-point row col)
                       (update-y-point row)
                       (#(scale-point width-sf height-sf %)))]
    (q/fill 0 255 0)
    (apply q/ellipse (conj from-point (* width-sf 50) (* height-sf 50)))))

(defn tile-label [width-sf height-sf row col]
  (as-> [hex-center [200 200]] line
    (update-x line row col)
    (update-y line row)
    (scale-line width-sf height-sf line)))

(defn gradient-color [percent]
  [(* 255 percent) 0 (* 255 (- 1 percent))])

(defn max-traveled [track]
  (->> track
       (map :traveled)
       (remove nil?)
       (apply max)))

(defn render-tile [tile width-sf height-sf max-travel]
  (let [{:keys [row col links]} tile
        link-lines (map #(link-line row col %) links)
        train (:train tile)
        traveled (if (:traveled tile) (:traveled tile) 0)
        color (gradient-color (/ traveled max-travel))]
    (println color)
    (q/stroke 0 0 0)
    (doseq [lines (scale width-sf height-sf (hex row col))]
      (apply q/line lines))
    (when train (draw-train row col train width-sf height-sf))
    (apply q/stroke color)
    (doseq [line (scale width-sf height-sf link-lines)]
      (apply q/line line))
    (q/text-size 20)
    (q/fill 0 0 0)
    (apply q/text (:id tile) (flatten (tile-label width-sf height-sf row col)))))

(defn render [track width height]
  (q/background 255 255 255)
  (q/stroke-weight 5)
  (q/stroke 0 0 0)
  (let [rows (->> track
                  (map :row)
                  (apply max)
                  (+ 2))
        cols (->> track
                  (map :col)
                  (apply max)
                  (+ 2))
        col-width (/ width cols)
        row-height (/ height rows)
        width-sf (double (/ col-width base-col-width))
        height-sf (double (/ row-height base-row-height))
        max-travel (max-traveled track)]
    (doseq [tile track]
      (render-tile tile width-sf height-sf max-travel))))
