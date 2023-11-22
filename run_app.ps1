# Navigate to the src directory
cd src

# Compile the Java application and place the output in the ../build directory
javac -d ../build/src main/java/mvc/controller/StartApp.java

# Navigate to the root directory
cd ..

# Run the compiled Java application
java -cp build/src main.java.mvc.controller.StartApp
