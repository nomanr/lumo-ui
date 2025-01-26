#!/bin/bash

set -e

PR_NUMBER=$(jq --raw-output .pull_request.number "$GITHUB_EVENT_PATH")
PR_URL="https://api.github.com/repos/$GITHUB_REPOSITORY/pulls/$PR_NUMBER"
PR_DATA=$(curl -s -H "Authorization: token $GITHUB_TOKEN" "$PR_URL")
LABELS=$(echo "$PR_DATA" | jq -r '.labels[].name')

if echo "$LABELS" | grep -q "Skip: Changelog"; then
  echo -e "\033[0;32m[Changelog] Skipping changelog check due to label 'Skip: Changelog'\033[0m"
  echo "CHANGELOG_EXISTS=false" >> $GITHUB_ENV
else
  CHANGED_FILES_URL="https://api.github.com/repos/$GITHUB_REPOSITORY/pulls/$PR_NUMBER/files"
  echo $CHANGED_FILES_URL
  CHANGED_FILES=$(curl -s -H "Authorization: token $GITHUB_TOKEN" "$CHANGED_FILES_URL" | jq -r '.[].filename')
echo $CHANGED_FILES
  if echo "$CHANGED_FILES" | grep -q "CHANGELOG.md"; then
    echo -e "\033[0;32mChangelog exists\033[0m"
    echo "CHANGELOG_EXISTS=true" >> $GITHUB_ENV
  else
    echo -e "\033[0;31mChangelog is missing\033[0m"
    echo "CHANGELOG_EXISTS=false" >> $GITHUB_ENV
    exit 1
  fi
fi