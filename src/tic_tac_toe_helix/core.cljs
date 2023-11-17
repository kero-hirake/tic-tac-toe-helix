(ns tic-tac-toe-helix.core
  (:require [helix.core :refer [defnc $ <>]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react" :as r]
            ["react-dom/client" :as rdom]))

(defnc square []
  (let [[value, set-value] (hooks/use-state nil)
        handle-click #(set-value "X")]
       (d/button {:class-name "square"
                  :on-click handle-click} 
                 value)))

(defnc board [] 
  (<>
   (d/div {:class-name "board-row"}
          ($ square)
          ($ square)
          ($ square)) 
   (d/div {:class-name "board-row"}
          ($ square)
          ($ square)
          ($ square))
   (d/div {:class-name "board-row"}
          ($ square)
          ($ square)
          ($ square))))

(defnc app [] 
  ($ board))

(defn init []
  ;; start your app with your favorite React renderer  
  (let [root (rdom/createRoot (js/document.getElementById "app"))]
    (.render root ($ r/StrictMode ($ app)))))  ; StrictMode
