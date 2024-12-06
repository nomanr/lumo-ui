#!/bin/bash

cd "$(dirname "$0")" || exit

SOURCE_DIR="../components-lab/src/main/java/com/nomanr/lumo/ui"
DEST_DIR="../plugin/src/main/resources/templates"

check_directory_exists() {
  local dir="$1"
  if [ ! -d "$dir" ]; then
    echo "Error: Directory not found -> $dir"
    exit 1
  fi
}

process_files() {
  local src="$1"
  local dest="$2"

  mkdir -p "$dest"

  for item in "$src"/*; do
    if [ -d "$item" ]; then
      process_files "$item" "$dest/$(basename "$item")"
    elif [ -f "$item" ]; then
      local filename=$(basename "$item")
      local dest_file="$dest/${filename%.kt}.kt.template"

      temp_file=$(mktemp)

      if ! sed -E -e "s|^package com\.nomanr\.lumo\.ui(\..*)?|package {{packageName}}\1|" \
                  -e "s|import com\.nomanr\.lumo\.ui(\..*)?|import {{packageName}}\1|" \
                  -e "s|AppTheme|{{themeName}}|g" \
                  -e "/Font\(R\.font\.poppins/d" \
                  "$item" > "$temp_file"; then
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

check_directory_exists "$SOURCE_DIR"
process_files "$SOURCE_DIR" "$DEST_DIR"
echo "Templates updated in $DEST_DIR"