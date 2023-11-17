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

(defnc board [] 
  (let [[squares set-squares] (hooks/use-state (vec (repeat 9 nil)))
        handle-click (fn [i] (set-squares (assoc squares i "X")))]
    (<>
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
