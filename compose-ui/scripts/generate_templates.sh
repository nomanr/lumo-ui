#!/bin/bash

SOURCE_DIR="components-lab/src/main/java/com/nomanr/compose/ui"
DEST_DIR="plugin/src/main/java/resources/templates"

process_files() {
  local src="$1"
  local dest="$2"

  mkdir -p "$dest"

  for item in "$src"/*; do
    if [ -d "$item" ]; then
      process_files "$item" "$dest/$(basename "$item")"
    elif [ -f "$item" ]; then
      local filename=$(basename "$item")
      local dest_file="$dest/$filename"

      sed 's/\bpackage\b/package {{PACKAGE_NAME}}/' "$item" > "$dest_file"
    fi
  done
}

process_files "$SOURCE_DIR" "$DEST_DIR"

echo "Template files generated in $DEST_DIR"