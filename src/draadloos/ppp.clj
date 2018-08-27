(ns draadloos.ppp)

(def conf (atom {:model {:huawei-e173 "usb_modeswitch -v 0x12d1 -p 0x1446 -J"
                         :other-model "not supported yet"}
                 :options {:file "/etc/ppp/options"
                           :content "ttyUSB0\n921600\nlock\ncrtscts\nmodem\npassive\nnovj\ndefaultroute\nnoipdefault\nusepeerdns\nnoauth\nhide-password\npersist\nholdoff 10\nmaxfail 0\ndebug"}
                 :provider {:file "/etc/ppp/peers/provider"
                            :content "file /etc/ppp/options\nconnect \"/usr/sbin/chat -v -t15 -f /etc/ppp/chatscripts/mobile-modem.chat\""}
                 :chat {:file "/etc/ppp/chatscripts/mobile-modem.chat"
                        :content {:huawei-e173-kpn-prepaid ["ABORT 'BUSY'"
                                                            "ABORT 'NO CARRIER'"
                                                            "ABORT 'VOICE'"
                                                            "ABORT 'NO DIALTONE'"
                                                            "ABORT 'NO DIAL TONE'"
                                                            "ABORT 'NO ANSWER'"
                                                            "ABORT 'DELAYED'"
                                                            "REPORT CONNECT"
                                                            "TIMEOUT 6"
                                                            "'' 'ATQ0'"
                                                            "'OK-AT-OK' 'ATZ'"
                                                            "TIMEOUT 3"
                                                            "'OK' 'AT+CPIN=0000'"
                                                            "'OK\\d-AT-OK' 'ATI'"
                                                            "'OK' 'ATZ'"
                                                            "'OK' 'ATQ0 V1 E1 S0=0 &C1 &D2 +FCLASS=0'"
                                                            "'OK' 'AT\\^SYSCFG=2,2,3fffffff,0,1'"
                                                            "'OK-AT-OK' 'AT+CGDCONT=1,\"IP\",\"portalmmm.nl\"'"
                                                            "'OK' 'ATDT*99#'"
                                                            "TIMEOUT 30"
                                                            "CONNECT ''"]
                                  :other-model-other-provider "no chatscript yet"}}}))

(defn add-newline [string] (str string "\n"))

;;(map println (vec (map add-newline chat)))


(defn update-pin [profile pin]
  ())

(let [profile (:huawei-e173-kpn-prepaid (:content (:chat @conf)))
      mod-str (-> profile
                  (nth 12)
                  (clojure.string/split #"CPIN=")
                  first
                  (str "CPIN=1234"))

(def arr [1 2 3 4])

(nth arr 2)

(split-at 2 arr)

(conj arr 3)






