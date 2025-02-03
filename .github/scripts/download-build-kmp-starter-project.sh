#!/bin/bash

CURRENT_VERSION="$1"
PROJECT_DIR="KMP-App-Template-main"

echo "Cleaning up previous files..."
rm -rf template.zip
rm -rf "$PROJECT_DIR"

echo "Downloading template from GitHub..."
curl -L -o template.zip https://github.com/Kotlin/KMP-App-Template/archive/refs/heads/main.zip

echo "Extracting template..."
unzip -q template.zip

if [ -d "$PROJECT_DIR" ]; then
    echo "Directory $PROJECT_DIR exists."
else
    echo "Directory $PROJECT_DIR does not exist after unzipping. Exiting..."
    exit 1
fi

echo "Changing directory to $PROJECT_DIR..."
cd "$PROJECT_DIR" || { echo "Failed to cd into $PROJECT_DIR. Exiting..."; exit 1; }

echo "Adding mavenLocal repository to settings.gradle.kts..."
awk '
/mavenCentral()/ {
    if (!seen) {
        print "        mavenLocal()"
        print "\n"
        seen=1
    }
}
{print}' settings.gradle.kts > temp && mv temp settings.gradle.kts

echo "Adding Lumo plugin to build.gradle.kts..."
awk 'NR==2 {print "    id(\"com.nomanr.plugin.lumo\") version \"'$CURRENT_VERSION'\""} {print}' build.gradle.kts > temp && mv temp build.gradle.kts

echo "Refreshing dependencies..."
./gradlew --refresh-dependencies

echo "Checking Lumo plugin help..."
./gradlew lumo --plugin-help

echo "Creating lumo.properties file..."
echo "

ThemeName=AppTheme
ComponentsDir=composeApp/src/commonMain/kotlin/com/jetbrains/kmpapp
PackageName=com.jetbrains.kmpapp
KotlinMultiplatform=true

" > lumo.properties

echo "Setting up Lumo..."
./gradlew lumo --setup

echo "Adding all components..."
./gradlew lumo --addAll

echo "Building project..."
./gradlew build
