
(ns draadloos.core
  (:gen-class))

(use 'clojure.java.shell)

(def app-state (atom {:ssid nil
                      :pwd nil
                      :cli true}))

(defn write-out []
  (spit "conf/stuff.conf" (:out (sh "wpa_passphrase" (:ssid @app-state) (:pwd @app-state)))))

(defn cli-n []
  (do
    (println "Welkom in draadloos. Voer uw netwerknaam in...")
    (while (:cli @app-state)
      (let [input (read-line)]
        (if (pos? (count input))
          (do
            (swap! app-state assoc :ssid input :cli false)
            (println "Voer uw wachtwoord in...")
            (swap! app-state assoc :cli true)
            (while (:cli @app-state)
              (let [input2 (read-line)]
                (if (pos? (count input2))
                  (do
                    (swap! app-state assoc :pwd input2)
                    (write-out)
                    (swap! app-state assoc :cli false)
                    (println "Tot ziens!")
                    (System/exit 0)))))))))))
                            

(defn -main
  [& args]
  (cli-n))




