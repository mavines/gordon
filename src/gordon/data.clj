(ns gordon.data)

(def single-track
  [{:id "a" :row 0 :col 0
    :links [[0 5]]}])

(def stuck
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

(def double-loop
  [{:id "a" :row 0 :col 0
    :links [[:se :e]]}
   {:id "b" :row 0 :col 1
    :links [[:w :e]]
    :train :e}
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

(def figure-eight
  [{:id "a" :row 0 :col 0
    :links [[:se :e]]}
   {:id "b" :row 0 :col 1
    :links [[:w :se]]}
   {:id "c" :row 0 :col 2
    :links []}
   {:id "d" :row 1 :col 0
    :links [[:sw :nw]]}
   {:id "e" :row 1 :col 1
    :links [[:sw :e] [:nw :e]]}
   {:id "f" :row 1 :col 2
    :links [[:w :ne] [:w :se]]}
   {:id "g" :row 2 :col 0
    :links [[:e :ne]]}
   {:id "h" :row 2 :col 1
    :links [[:w :ne]]}
   {:id "i" :row 2 :col 2
    :links []}
   {:id "j" :row 0 :col 3
    :links [[:sw :e]]}
   {:id "k" :row 0 :col 4
    :links [[:w :sw]]}
   {:id "l" :row 1 :col 3
    :links [[:ne :se]]}
   {:id "m" :row 2 :col 4
    :links [[:nw :w]]}
   {:id "n" :row 2 :col 3
    :links [[:e :nw]]}])
