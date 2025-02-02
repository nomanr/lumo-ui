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
NEW_TAG_VERSION="v$MAJOR.$MINOR.$PATCH"

if [[ -n "$GITHUB_ENV" ]]; then
  echo "NEW_VERSION=$NEW_VERSION" >> "$GITHUB_ENV"
  echo "NEW_TAG_VERSION=$NEW_TAG_VERSION" >> "$GITHUB_ENV"
fi

ESCAPED_CURRENT_VERSION=$(echo "$CURRENT_VERSION" | sed 's/\./\\./g')
sed -i "s/version\s*=\s*\"$ESCAPED_CURRENT_VERSION\"/version = \"$NEW_VERSION\"/" "$GRADLE_FILE"

UPDATED_VERSION=$(grep -oP 'version\s*=\s*"\K[0-9]+\.[0-9]+\.[0-9]+' "$GRADLE_FILE")

if [[ "$UPDATED_VERSION" != "$NEW_VERSION" ]]; then
  exit 1
fi

git config --global user.name "github-actions"
git config --global user.email "github-actions@github.com"
git add "$GRADLE_FILE"
git commit --amend --no-edit
git push --force
