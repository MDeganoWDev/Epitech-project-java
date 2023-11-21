#!/bin/bash

# Create the build directory if it doesn't exist
mkdir -p build

# Navigate to the src directory
cd src

# Compile the Java application and place the output in the ../build directory
javac -d ../build/src main/java/mvc/controller/StartApp.java

# Navigate to the build directory
cd ../build/src

# Run the compiled Java application
java main.java.mvc.controller.StartApp

# Return to the original directory
cd ..
