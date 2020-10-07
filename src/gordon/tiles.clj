(ns gordon.tiles)

(defn abs [n]
  (if (< n 0)
    (* -1 n)
    n))

(defn north-west? [tile other-tile]
  (let [{:keys [row col]} tile
        {other-row :row
         other-col :col} other-tile]
    (and (= -1 (- other-row row))
         (if (even? row)
           (= -1 (- other-col col))
           (= 0 (- other-col col))))))

(defn north-east? [tile other-tile]
  (let [{:keys [row col]} tile
        {other-row :row
         other-col :col} other-tile]
    (and (= -1 (- other-row row))
         (if (even? row)
           (= 0 (- other-col col))
           (= 1 (- other-col col))))))

(defn west? [tile other-tile]
  (let [{:keys [row col]} tile
        {other-row :row
         other-col :col} other-tile]
    (and (= other-row row)
         (= -1 (- other-col col)))))

(defn east? [tile other-tile]
  (let [{:keys [row col]} tile
        {other-row :row
         other-col :col} other-tile]
    (and (= other-row row)
         (= 1 (- other-col col)))))

(defn south-west? [tile other-tile]
  (let [{:keys [row col]} tile
        {other-row :row
         other-col :col} other-tile]
    (and (= 1 (- other-row row))
         (if (even? row)
           (= -1 (- other-col col))
           (= 0 (- other-col col))))))

(defn south-east? [tile other-tile]
  (let [{:keys [row col]} tile
        {other-row :row
         other-col :col} other-tile]
    (and (= 1 (- other-row row))
         (if (even? row)
           (= 0 (- other-col col))
           (= 1 (- other-col col))))))

(defn neighbor? [tile other-tile]
  (let [{:keys [row col links]} tile
        other-row (:row other-tile)
        other-col (:col other-tile)
        row-diff (- other-row row)
        col-diff (- other-col col)]
    (cond
      (= 0 row-diff) (= 1 (abs col-diff))
      (and (even? row)
           (= 1 (abs row-diff))) (or (= 0 col-diff)
                                     (= -1 col-diff))
      (and (odd? row)
           (= 1 (abs row-diff))) (or (= 0 col-diff)
                                     (= 1 col-diff)))))

(defn north-west-link? [links other-links]
  (and (some #(= :nw %) (flatten links))
       (some #(= :se %) (flatten other-links))))

(defn north-east-link? [links other-links]
  (and (some #(= :ne %) (flatten links))
       (some #(= :sw %) (flatten other-links))))

(defn west-link? [links other-links]
  (and (some #(= :w %) (flatten links))
       (some #(= :e %) (flatten other-links))))

(defn east-link? [links other-links]
  (west-link? other-links links))

(defn south-west-link? [links other-links]
  (north-east-link? other-links links))

(defn south-east-link? [links other-links]
  (north-west-link? other-links links))

(defn connected-sides [tile incoming]
  (let [links (:links tile)]
    (->> (filter #(some #{incoming} %) links)
         (flatten)
         (filter #(not= incoming %)))))

(defn find-next-link-sides [tile other-tile]
  (let [links (:links tile)
        other-links (:links other-tile)]
    (cond
      (north-west? tile other-tile) (connected-sides other-tile :se)
      (north-east? tile other-tile) (connected-sides other-tile :sw)
      (west? tile other-tile) (connected-sides other-tile :e)
      (east? tile other-tile) (connected-sides other-tile :w)
      (south-west? tile other-tile) (connected-sides other-tile :ne)
      (south-east? tile other-tile) (connected-sides other-tile :nw)
      :else false)))

(defn in-direction? [tile direction other-tile]
  (condp = direction
    :e (east? tile other-tile)
    :w (west? tile other-tile)
    :nw (north-west? tile other-tile)
    :ne (north-east? tile other-tile)
    :sw (south-west? tile other-tile)
    :se (south-east? tile other-tile)
    :else false))

(defn next-tile-position [track tile out-direction]
  (condp = out-direction
    :e (first (filter #(east? tile %) track))
    :w (first (filter #(west? tile %) track))
    :ne (first (filter #(north-east? tile %) track))
    :nw (first (filter #(north-west? tile %) track))
    :se (first (filter #(south-east? tile %) track))
    :sw (first (filter #(south-west? tile %) track))))

(defn opposite-entrance [entrance]
  (condp = entrance
    :e :w
    :w :e
    :nw :se
    :ne :sw
    :se :nw
    :sw :ne))
