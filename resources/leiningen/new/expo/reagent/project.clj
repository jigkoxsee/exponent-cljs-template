(defproject {{name}} "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.439"]
                 [binaryage/oops "0.6.3"]
                 [reagent "0.7.0" :exclusions [cljsjs/react cljsjs/react-dom cljsjs/react-dom-server cljsjs/create-react-class]]
                 [re-frame "0.10.6"]
                 [react-native-externs "0.2.0"]]
            :plugins [[lein-cljsbuild "1.1.7"]
                      [lein-figwheel "0.5.14"]]
            :clean-targets ["target/" "main.js"]
            :aliases {"figwheel"        ["run" "-m" "user" "--figwheel"]
                      "rebuild-modules" ["run" "-m" "user" "--rebuild-modules"]
                      "prod-build"      ^{:doc "Recompile code with prod profile."}
                                        ["with-profile" "prod" "cljsbuild" "once" "main"]}
            :profiles {:dev  {:dependencies [[figwheel-sidecar "0.5.14"]
                                             [com.cemerick/piggieback "0.2.2"]]
                              :source-paths ["src" "env/dev"]
                              :cljsbuild    {:builds [{:id           "main"
                                                       :source-paths ["src" "env/dev"]
                                                       :figwheel     true
                                                       :compiler     {:output-to     "target/expo/not-used.js"
                                                                      :main          "env.expo.main"
                                                                      :output-dir    "target/expo"
                                                                      :optimizations :none}}]}
                              :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}
                       :prod {:cljsbuild {:builds [{:id           "main"
                                                    :source-paths ["src" "env/prod"]
                                                    :compiler     {:output-to          "main.js"
                                                                   :main               "env.expo.main"
                                                                   :output-dir         "target/expo"
                                                                   :static-fns         true
                                                                   :externs            ["js/externs.js"]
                                                                   :infer-externs      true
                                                                   :parallel-build     true
                                                                   :optimize-constants true
                                                                   :optimizations      :advanced
                                                                   :closure-defines    {"goog.DEBUG" false}}}]}}})
