#!/bin/bash

set -e

RELEASE_TYPE="$1"
GRADLE_FILE="lumo-ui/plugin/build.gradle.kts"

if [[ -z "$RELEASE_TYPE" ]]; then
  exit 1
fi

if [[ ! -f "$GRADLE_FILE" ]]; then
  exit 1
fi

CURRENT_VERSION=$(grep -oP 'version\s*=\s*"\K[0-9]+\.[0-9]+\.[0-9]+' "$GRADLE_FILE")

if [[ -z "$CURRENT_VERSION" ]]; then
  exit 1
fi

IFS='.' read -r MAJOR MINOR PATCH <<< "$CURRENT_VERSION"

if ! [[ "$MAJOR" =~ ^[0-9]+$ && "$MINOR" =~ ^[0-9]+$ && "$PATCH" =~ ^[0-9]+$ ]]; then
  exit 1
fi

case "$RELEASE_TYPE" in
  major) MAJOR=$((MAJOR + 1)); MINOR=0; PATCH=0 ;;
  minor) MINOR=$((MINOR + 1)); PATCH=0 ;;
  patch) PATCH=$((PATCH + 1)) ;;
  *) exit 1 ;;
esac

NEW_VERSION="$MAJOR.$MINOR.$PATCH"

ESCAPED_CURRENT_VERSION=$(echo "$CURRENT_VERSION" | sed 's/\./\\./g')
sed -i "s/version\s*=\s*\"$ESCAPED_CURRENT_VERSION\"/version = \"$NEW_VERSION\"/" "$GRADLE_FILE"

UPDATED_VERSION=$(grep -oP 'version\s*=\s*"\K[0-9]+\.[0-9]+\.[0-9]+' "$GRADLE_FILE")

if [[ "$UPDATED_VERSION" != "$NEW_VERSION" ]]; then
  exit 1
fi

echo $NEW_VERSION
