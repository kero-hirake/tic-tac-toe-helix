(ns tic-tac-toe-helix.core
  (:require [helix.core :refer [defnc $ <>]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react" :as r]
            ["react-dom/client" :as rdom]))

(defnc square [{:keys [value]}]
  (d/button {:class-name "square"} value))

(defnc board [] 
  (<>
   (d/div {:class-name "board-row"}
          ($ square {:value 1})
          ($ square {:value 2})
          ($ square {:value 3})
             ) 
   (d/div {:class-name "board-row"}
          ($ square {:value 4})
          ($ square {:value 5})
          ($ square {:value 6}))
   (d/div {:class-name "board-row"}
          ($ square {:value 7})
          ($ square {:value 8})
          ($ square {:value 9}))
   ))

(defnc app [] 
  ($ board))

(defn init []
  ;; start your app with your favorite React renderer  
  (let [root (rdom/createRoot (js/document.getElementById "app"))]
    (.render root ($ r/StrictMode ($ app)))))  ; StrictMode
