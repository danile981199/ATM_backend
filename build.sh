#!/bin/bash

echo "Running Checkstyle..."
java -jar lib/checkstyle-10.3-all.jar -c /google_checks.xml src/

echo "Compiling..."
javac -cp "lib/*" -d out $(find src -name "*.java")

echo "Generating JavaDoc..."
javadoc -d docs -cp "lib/*" $(find src -name "*.java")

echo "Running Tests..."
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path out --scan-class-path

