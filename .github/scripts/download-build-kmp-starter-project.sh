#!/bin/bash

set -e

CURRENT_VERSION="$1"
PROJECT_DIR="KMP-App-Template-main"
COMPONENTS_DIR="composeApp/src/commonMain/kotlin/com/jetbrains/kmpapp/components"

rm -rf template.zip
rm -rf "$PROJECT_DIR"

COMPOSABLE_VERSION=$(find . -name "libs.versions.toml" -exec awk -F ' = "' '/^nomanr-composables/ {gsub(/"/, "", $2); if ($2 ~ /^[0-9]+\.[0-9]+\.[0-9]+$/) print $2}' {} +)

curl -L -o template.zip https://github.com/Kotlin/KMP-App-Template/archive/refs/heads/main.zip

unzip -q template.zip

if [ ! -d "$PROJECT_DIR" ]; then
    exit 1
fi

cd "$PROJECT_DIR" || exit 1

sed -i '/commonMain\.dependencies {/,/}/ {/implementation/!s/\(commonMain\.dependencies {\)/\1\n            implementation("com.nomanr:composables:'"$COMPOSABLE_VERSION"'")/}' composeApp/build.gradle.kts

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
