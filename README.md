 Instructions:\
 -Install JavaFX sdk from\ 
    https://gluonhq.com/products/javafx/
    
 -Export the javafx/lib folder path\
    `export PFX='Path/To/JavaFX/lib'`
   
-Compile java source files\
    `javac --module-path $PFX --add-modules javafx.controls,javafx.base,javafx.fxml src/*.java -d build/'`

-Enter build dir\
    `cd build`
    
-Create jar\
    `jar cmf EH.mf EH.jar *.class`
    
-Run jar\
    `java --module-path $PFX --add-modules javafx.controls,javafx.base,javafx.fxml -jar EH.jar`\
     if GPU isn't enabled by default try\
     `java --module-path $PFX --add-modules javafx.controls,javafx.base,javafx.fxml -Dprism.forceGPU=true -jar EH.jar`\
\
![EventHorizon](https://user-images.githubusercontent.com/71713194/149700132-b0062c1a-6f5d-4c8c-8f80-3273cb5d8d47.PNG)


![Space2](https://user-images.githubusercontent.com/71713194/149699375-d58b385b-5924-4e37-8b9c-34a89b366265.gif)
![Space34gif](https://user-images.githubusercontent.com/71713194/149699629-6eff161f-3baf-4476-a053-b0517c7cb450.gif)
![Spacefire](https://user-images.githubusercontent.com/71713194/149699709-99c1ebef-d212-4385-af81-6ea9525022df.gif)


