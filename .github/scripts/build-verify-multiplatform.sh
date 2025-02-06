#!/bin/bash

set -e

rm -rf sample-multiplatform/ui-components/src/commonMain/kotlin/com/nomanr/lumo/multiplatform/ui/foundation
rm -rf sample-multiplatform/ui-components/src/commonMain/kotlin/com/nomanr/lumo/multiplatform/ui/components
rm -rf sample-multiplatform/ui-components/src/androidMain/kotlin/com/nomanr/lumo/multiplatform/ui/foundation
rm -rf sample-multiplatform/ui-components/src/androidMain/kotlin/com/nomanr/lumo/multiplatform/ui/components
rm -rf sample-multiplatform/ui-components/src/iosMain/kotlin/com/nomanr/lumo/multiplatform/ui/foundation
rm -rf sample-multiplatform/ui-components/src/iosMain/kotlin/com/nomanr/lumo/multiplatform/ui/components

rm -rf lumo.properties
cat <<EOF > lumo.properties
ThemeName=AppTheme
ComponentsDir=sample-multiplatform/ui-components/src/commonMain/kotlin/com/nomanr/lumo/multiplatform/ui
PackageName=com.nomanr.lumo.multiplatform.ui
KotlinMultiplatform=true
EOF

./gradlew lumo --add-all
./gradlew sample-multiplatform:catalogue:android:assembleDebug