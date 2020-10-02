(ns gordon.data)

(def single-track
  [{:id "a" :row 0 :col 0
    :links [[0 5]]}])

(def stuck-track
  [{:id "a" :row 0 :col 0
    :links [[:se :e]]}
   {:id "b" :row 0 :col 1
    :links [[:w :e]]}
   {:id "c" :row 0 :col 2
    :links [[:w :se] [:se :sw]]}
   {:id "d" :row 1 :col 0
    :links [[:nw :se]]}
   {:id "e" :row 1 :col 1
    :links [[:ne :sw]]}
   {:id "f" :row 1 :col 2
    :links [[:nw :sw]]}
   {:id "g" :row 2 :col 0
    :links []}
   {:id "h" :row 2 :col 1
    :links [[:nw :e] [:nw :ne]]}
   {:id "i" :row 2 :col 2
    :links [[:w :ne]]}])

(def double-loop-track
  [{:id "a" :row 0 :col 0
    :links [[:se :e]]}
   {:id "b" :row 0 :col 1
    :links [[:w :e]]}
   {:id "c" :row 0 :col 2
    :links [[:w :se] [:w :sw]]}
   {:id "d" :row 1 :col 0
    :links [[:nw :se]]}
   {:id "e" :row 1 :col 1
    :links [[:ne :sw]]}
   {:id "f" :row 1 :col 2
    :links [[:nw :sw]]}
   {:id "g" :row 2 :col 0
    :links []}
   {:id "h" :row 2 :col 1
    :links [[:nw :e] [:nw :ne]]}
   {:id "i" :row 2 :col 2
    :links [[:w :ne]]}])
