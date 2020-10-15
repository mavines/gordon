(ns gordon.data)

(def single-track
  [{:id "a" :row 0 :col 0
    :links [[0 5]]}])

(def stuck
  [{:id "a" :row 0 :col 0
    :links [[:se :e]]
    :train :e}
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
    :links [[:se :e]]
    :train :e}
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

(def big-track
  [{:id "z" :row 0 :col 0
    :links []}
   {:id "x" :row 0 :col 1
    :links []}
   {:id "a" :row 0 :col 2
    :links [[:se :e]]
    :train :e}
   {:id "b" :row 0 :col 3
    :links [[:w :e]]}
   {:id "c" :row 0 :col 4
    :links [[:w :e]]}
   {:id "j" :row 0 :col 5
    :links [[:w :sw]]}
   {:id "k" :row 0 :col 6
    :links [[:se :sw]]}
   {:id "w" :row 1 :col 0
    :links [[:sw :e]]}
   {:id "y" :row 1 :col 1
    :links [[:w :e]]}
   {:id "d" :row 1 :col 2
    :links [[:nw :e] [:e :w] [:se :w]]}
   {:id "e" :row 1 :col 3
    :links [[:e :w][:se :sw]]}
   {:id "f" :row 1 :col 4
    :links [[:ne :w] [:w :e]]}
   {:id "l" :row 1 :col 5
    :links [[:w :ne] [:w :e]]}
   {:id "o" :row 1 :col 6
    :links [[:nw :w]]}
   {:id "u" :row 2 :col 0
    :links [[:ne :se] [:se :e]]}
   {:id "v" :row 2 :col 1
    :links [[:w :e]]}
   {:id "g" :row 2 :col 2
    :links [[:w :sw] [:sw :e]]}
   {:id "h" :row 2 :col 3
    :links [[:w :e][:ne :nw][:sw :se]]}
   {:id "i" :row 2 :col 4
    :links [[:w :se] [:se :nw][:sw :e]]}
   {:id "m" :row 2 :col 6
    :links [[:w :sw]]}
   {:id "n" :row 2 :col 5
    :links [[:w :e]]}
   {:id "30" :row 3 :col 0
    :links [[:nw :e]]}
   {:id "31" :row 3 :col 1
    :links [[:ne :sw] [:w :e]]}
   {:id "32" :row 3 :col 2
    :links [[:w :e][:se :ne]]}
   {:id "33" :row 3 :col 3
    :links [[:w :e][:w :se][:ne :nw]]}
   {:id "34" :row 3 :col 4
    :links [[:w :e] [:nw :e]]}
   {:id "35" :row 3 :col 5
    :links [[:ne :w]]}
   {:id "36" :row 3 :col 6
    :links []}
   {:id "40" :row 4 :col 0
    :links []}
   {:id "41" :row 4 :col 1
    :links [[:ne :se]]}
   {:id "42" :row 4 :col 2
    :links [[:sw :e]]}
   {:id "43" :row 4 :col 3
    :links [[:w :e][:se :nw]]}
   {:id "44" :row 4 :col 4
    :links [[:nw :e] [:w :e]]}
   {:id "45" :row 4 :col 5
    :links [[:w :e]]}
   {:id "46" :row 4 :col 6
    :links [[:w :se]]}
   {:id "50" :row 5 :col 0
    :links []}
   {:id "51" :row 5 :col 1
    :links [[:nw :ne]]}
   {:id "52" :row 5 :col 2
    :links []}
   {:id "53" :row 5 :col 3
    :links [[:e :nw]]}
   {:id "54" :row 5 :col 4
    :links [[:e :w]]}
   {:id "55" :row 5 :col 5
    :links [[:e :w]]}
   {:id "56" :row 5 :col 6
    :links [[:nw :w]]}])
