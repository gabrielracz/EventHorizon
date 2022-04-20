#!/bin/bash
javac --module-path ./lib/ --add-modules javafx.controls,javafx.base,javafx.fxml src/*.java -d build/
cd build
jar cmf EH.mf EH.jar *.class
