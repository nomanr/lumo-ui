#!/bin/bash

SOURCE_DIR="../components-lab/src/main/java/com/nomanr/compose/ui"
DEST_DIR="../plugin/src/main/java/resources/templates"

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

      if ! sed -e "s/^package com\.nomanr\.compose\.ui\.\(.*\)/package {{packageName}}.\1/" \
               -e 's/import com\.nomanr\.compose\.ui\.\(.*\)/import {{packageName}}.\1/' \
               "$item" > "$dest_file"; then
        echo "Error: Failed to process file -> $item"
        exit 1
      fi
    else
      echo "Warning: Skipping unrecognized item -> $item"
    fi
  done
}

echo "Running script from: $(pwd)"
check_directory_exists "$SOURCE_DIR"
process_files "$SOURCE_DIR" "$DEST_DIR"
echo "Template files successfully generated in $DEST_DIR"