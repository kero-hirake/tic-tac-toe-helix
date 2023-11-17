(ns tic-tac-toe-helix.core
  (:require [helix.core :refer [defnc $ <>]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react" :as r]
            ["react-dom/client" :as rdom]))

(defnc square [{:keys [value on-click]}] 
  (d/button {:class-name "square"
             :on-click on-click} 
            value))

(defn calcurate-winner [squares]
  (let [lines [[0 1 2]
               [3 4 5]
               [6 7 8]
               [0 3 6]
               [1 4 7]
               [2 5 8]
               [0 4 8]
               [2 4 6]]]
    (some-> (filter (fn [[a b c]]
                      (and (squares a)
                           (= (squares a)
                              (squares b)
                              (squares c))))
                    lines)
            first
            (#(squares (first %))))))

(defnc board [] 
  (let [[x-is-next? set-x-is-next?] (hooks/use-state true)
        [squares set-squares] (hooks/use-state (vec (repeat 9 nil))) 
        winner (calcurate-winner squares)
        status (if winner
                 (str "Winner: " winner)
                 (str "Next player: " (if x-is-next? "X" "O")))
        handle-click (fn [i]
                       (when-not (or (get squares i)
                                      winner)  
                         (set-squares (assoc squares i (if x-is-next? "X" "O")))
                         (set-x-is-next? (not x-is-next?))))] 
    
    (<>
     (d/div {:class-name "status"} status)
     (d/div {:class-name "board-row"}
            ($ square {:value (get squares 0) :on-click #(handle-click 0)})
            ($ square {:value (get squares 1) :on-click #(handle-click 1)})
            ($ square {:value (get squares 2) :on-click #(handle-click 2)})) 
     (d/div {:class-name "board-row"}
            ($ square {:value (get squares 3) :on-click #(handle-click 3)})
            ($ square {:value (get squares 4) :on-click #(handle-click 4)})
            ($ square {:value (get squares 5) :on-click #(handle-click 5)}))
     (d/div {:class-name "board-row"}
            ($ square {:value (get squares 6) :on-click #(handle-click 6)})
            ($ square {:value (get squares 7) :on-click #(handle-click 7)})
            ($ square {:value (get squares 8) :on-click #(handle-click 8)})))))

(defnc app [] 
  ($ board))

(defn init []
  ;; start your app with your favorite React renderer  
  (let [root (rdom/createRoot (js/document.getElementById "app"))]
    (.render root ($ r/StrictMode ($ app)))))  ; StrictMode
