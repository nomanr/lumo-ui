#!/bin/bash

terminate_on_error() {
  if [ $1 -ne 0 ]; then
    echo "Error: $2"
    exit 1
  fi
}

echo "Starting Gradle Clean and Build Script..."

echo "Step 1: Cleaning and building the project..."
./gradlew clean build
terminate_on_error $? "Gradle clean build failed."

echo "Step 2: Removing unwanted directories..."
rm -rf sample-android/ui-components/src/main/java/com/nomanr/sample/ui/foundation
rm -rf sample-android/ui-components/src/main/java/com/nomanr/sample/ui/components
terminate_on_error $? "Failed to remove directories."


echo "Step 3: Generating all components..."
./gradlew lumo --addAll
terminate_on_error $? "Failed to generate components."

echo "Step 4: Rebuilding the project (excluding Spotless check)..."
./gradlew build -x spotlessCheck
terminate_on_error $? "Gradle build (without Spotless check) failed."

echo "Step 5: Building the specific module: sample-android:catalogue:assembleDebug..."
./gradlew sample-android:catalogue:assembleDebug
terminate_on_error $? "Module build failed."

echo "All steps completed successfully!"