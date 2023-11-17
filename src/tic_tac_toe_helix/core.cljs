(ns tic-tac-toe-helix.core
  (:require [helix.core :refer [defnc $ <>]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react" :as r]
            ["react-dom/client" :as rdom]))

(defnc board [] 
  (<>
   (d/div {:class-name "board-row"}
             (d/button {:class-name "square"
                        :on-click #(println "clicked")} "1")
             (d/button {:class-name "square"} "2")
             (d/button {:class-name "square"} "3")) 
   (d/div {:class-name "board-row"}
             (d/button {:class-name "square"} "4")
             (d/button {:class-name "square"} "5")
             (d/button {:class-name "square"} "6"))
   (d/div {:class-name "board-row"}
             (d/button {:class-name "square"} "7")
             (d/button {:class-name "square"} "8")
             (d/button {:class-name "square"} "9"))
   ))

(defnc app [] 
  ($ board))

(defn init []
  ;; start your app with your favorite React renderer  
  (let [root (rdom/createRoot (js/document.getElementById "app"))]
    (.render root ($ r/StrictMode ($ app)))))  ; StrictMode
