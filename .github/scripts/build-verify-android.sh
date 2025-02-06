#!/bin/bash

set -e

rm -rf sample-android/ui-components/src/main/java/com/nomanr/sample/ui/foundation
rm -rf sample-android/ui-components/src/main/java/com/nomanr/sample/ui/components

rm -rf lumo.properties
cat <<EOF > lumo.properties
ThemeName=AppTheme

ComponentsDir=sample-android/ui-components/src/main/java/com/nomanr/sample/ui
PackageName=com.nomanr.sample.ui
EOF

./gradlew lumo --add-all
./gradlew sample-android:catalogue:assembleDebug