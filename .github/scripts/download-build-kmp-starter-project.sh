#!/bin/bash

set -e

CURRENT_VERSION="$1"
PROJECT_DIR="KMP-App-Template-main"
COMPONENTS_DIR="composeApp/src/commonMain/kotlin/com/jetbrains/kmpapp/components"

rm -rf template.zip
rm -rf "$PROJECT_DIR"

curl -L -o template.zip https://github.com/Kotlin/KMP-App-Template/archive/refs/heads/main.zip

unzip -q template.zip

if [ ! -d "$PROJECT_DIR" ]; then
    exit 1
fi

cd "$PROJECT_DIR" || exit 1

awk '/mavenCentral()/ { if (!seen) { print "        mavenLocal()"; print "\n"; seen=1 } } {print}' settings.gradle.kts > temp && mv temp settings.gradle.kts

awk 'NR==2 {print "    id(\"com.nomanr.plugin.lumo\") version \"'$CURRENT_VERSION'\""} {print}' build.gradle.kts > temp && mv temp build.gradle.kts

./gradlew --refresh-dependencies
./gradlew lumo --plugin-help

echo "

ThemeName=AppTheme
ComponentsDir=$COMPONENTS_DIR
PackageName=com.jetbrains.kmpapp.components
KotlinMultiplatform=true

" > lumo.properties

mkdir -p "$COMPONENTS_DIR"

./gradlew lumo --setup

OUTPUT=$(./gradlew lumo --available-components)

RAW_COMPONENTS=$(echo "$OUTPUT" | awk '/Available components:/ {flag=1; next} flag && NF {gsub(/^-/, "", $0); print $0} flag && !NF {exit}')

COMPONENTS=()
while IFS= read -r line; do
    [[ -n "$line" ]] && COMPONENTS+=("$line")
done <<< "$RAW_COMPONENTS"

if [ ${#COMPONENTS[@]} -eq 0 ]; then
    exit 1
fi

for COMPONENT in "${COMPONENTS[@]}"; do
    rm -rf "$COMPONENTS_DIR/components"
    ./gradlew lumo --add "$COMPONENT"
    ./gradlew assembleDebug
done
