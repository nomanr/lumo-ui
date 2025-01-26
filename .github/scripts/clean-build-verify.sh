#!/bin/bash

# Exit immediately if a command exits with a non-zero status
set -e

echo "Starting Gradle Clean and Build Script..."

echo "Step 1: Cleaning and building the project..."
./gradlew clean build

echo "Step 2: Removing unwanted directories..."
rm -rf sample-android/ui-components/src/main/java/com/nomanr/sample/ui/foundation
rm -rf sample-android/ui-components/src/main/java/com/nomanr/sample/ui/components

echo "Step 3: Generating all components..."
./gradlew lumo --addAll

echo "Step 4: Rebuilding the project (excluding Spotless check)..."
./gradlew build -x spotlessCheck

echo "Step 5: Building the specific module: sample-android:catalogue:assembleDebug..."
./gradlew sample-android:catalogue:assembleDebug

echo "All steps completed successfully!"