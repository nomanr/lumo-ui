#!/bin/bash

cd "$(dirname "$0")" || exit

ANDROID_NATIVE_SOURCE_DIR="../components-lab/src/androidMain/kotlin/com/nomanr/lumo/ui/components/native"
ANDROID_SOURCE_DIR="../components-lab/src/androidMain/kotlin/com/nomanr/lumo/ui"
COMMON_SOURCE_DIR="../components-lab/src/commonMain/kotlin/com/nomanr/lumo/ui"
IOS_SOURCE_DIR="../components-lab/src/iosMain/kotlin/com/nomanr/lumo/ui"
MAC_OS_SOURCE_DIR="../components-lab/src/macosMain/kotlin/com/nomanr/lumo/ui"
DESKTOP_SOURCE_DIR="../components-lab/src/desktopMain/kotlin/com/nomanr/lumo/ui"

ANDROID_DEST_DIR="../plugin/src/main/resources/templates/android"
ANDROID_DEST_COMPONENTS_DIR="$ANDROID_DEST_DIR/components"
MULTIPLATFORM_DEST_DIR="../plugin/src/main/resources/templates/multiplatform"

check_directory_exists() {
  local dir="$1"
  if [ ! -d "$dir" ]; then
    echo "Error: Directory not found -> $dir"
    exit 1
  fi
}

delete_existing_templates() {
  local dir="$1"
  if [ -d "$dir" ]; then
    rm -rf "$dir"
  fi
}

process_files() {
  local src="$1"
  local dest="$2"
  local is_android="$3"

  mkdir -p "$dest"

  for item in "$src"/*; do
    # Skip the "native" directory if not generating for Android
    if [ "$is_android" != "true" ] && [[ "$(basename "$item")" == "native" ]]; then
      echo "Skipping 'native' directory for non-Android templates: $item"
      continue
    fi

    if [ -d "$item" ]; then
      process_files "$item" "$dest/$(basename "$item")" "$is_android"
    elif [ -f "$item" ]; then
      local filename=$(basename "$item")
      local dest_file="$dest/${filename%.kt}.kt.template"

      temp_file=$(mktemp)

      sed_command=(
        -e "s|^package com\.nomanr\.lumo\.ui\(.*\)|package {{packageName}}\1|"
        -e "s|import com\.nomanr\.lumo\.ui\(.*\)|import {{packageName}}\1|"
        -e "s|AppTheme|{{themeName}}|g"
        -e "/^import com\.nomanr\.lumo\.components\.R$/d"
      )

      if [ "$is_android" == "true" ]; then
        sed_command+=(
          -e "s|import org.jetbrains.compose.ui.tooling.preview.Preview|import androidx.compose.ui.tooling.preview.Preview|g"
          -e "s|\\.components\\.native|\\.components|g"
        )
      fi

      if ! sed "${sed_command[@]}" "$item" > "$temp_file"; then
        echo "Error: Failed to process file -> $item"
        rm -f "$temp_file"
        exit 1
      fi

      if [ -f "$dest_file" ] && cmp -s "$temp_file" "$dest_file"; then
        rm -f "$temp_file"
      else
        mv "$temp_file" "$dest_file"
        echo "Template generated: $dest_file"
      fi
    else
      echo "Warning: Skipping unrecognized item -> $item"
    fi
  done
}
check_directory_exists "$ANDROID_SOURCE_DIR"
check_directory_exists "$COMMON_SOURCE_DIR"
check_directory_exists "$ANDROID_NATIVE_SOURCE_DIR"

delete_existing_templates "$ANDROID_DEST_DIR"
delete_existing_templates "$ANDROID_DEST_COMPONENTS_DIR"
delete_existing_templates "$MULTIPLATFORM_DEST_DIR"

# Generating Android Templates
process_files "$COMMON_SOURCE_DIR" "$ANDROID_DEST_DIR" "true"
process_files "$ANDROID_NATIVE_SOURCE_DIR" "$ANDROID_DEST_COMPONENTS_DIR" "true"

# Generating Multiplatform Templates
process_files "$COMMON_SOURCE_DIR" "$MULTIPLATFORM_DEST_DIR"
process_files "$ANDROID_SOURCE_DIR" "$MULTIPLATFORM_DEST_DIR"
process_files "$IOS_SOURCE_DIR" "$MULTIPLATFORM_DEST_DIR"
process_files "$MAC_OS_SOURCE_DIR" "$MULTIPLATFORM_DEST_DIR"
process_files "$DESKTOP_SOURCE_DIR" "$MULTIPLATFORM_DEST_DIR"

echo "Templates have been updated."