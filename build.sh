#!/bin/bash
set -e  # exit if any command fails

echo "Formatting check..."
# You can add a formatter here if desired

echo "Compiling Java files..."
mkdir -p out
# Exclude test directory
find src -name "*.java" ! -path "src/test/*" > compile_sources.txt
javac -d out @compile_sources.txt

echo "Compilation successful."

echo "Generating documentation..."
find src -name "*.java" ! -path "src/test/*" > doc_sources.txt
javadoc -d docs @doc_sources.txt

echo "Javadoc generated at docs/index.html"

echo "Run unit tests in IntelliJ manually if needed"
