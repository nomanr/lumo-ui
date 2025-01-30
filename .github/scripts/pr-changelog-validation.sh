#!/bin/bash

set -e

# Extract PR number and URL
PR_NUMBER=$(jq --raw-output .pull_request.number "$GITHUB_EVENT_PATH")
PR_URL="https://api.github.com/repos/$GITHUB_REPOSITORY/pulls/$PR_NUMBER"

# Fetch PR data
PR_DATA=$(curl -s -H "Authorization: token $GITHUB_TOKEN" "$PR_URL")
LABELS=$(echo "$PR_DATA" | jq -r '.labels[].name')
AUTHOR=$(echo "$PR_DATA" | jq -r '.user.login')

# Check if the PR is labeled to skip changelog
if echo "$LABELS" | grep -q "Skip: Changelog"; then
  echo -e "\033[0;32m[Changelog] Skipping changelog check due to label 'Skip: Changelog'\033[0m"
  echo "CHANGELOG_EXISTS=false" >> $GITHUB_ENV
  exit 0
fi

# Fetch changed files in the PR
CHANGED_FILES_URL="https://api.github.com/repos/$GITHUB_REPOSITORY/pulls/$PR_NUMBER/files"
CHANGED_FILES=$(curl -s -H "Authorization: token $GITHUB_TOKEN" "$CHANGED_FILES_URL" | jq -r '.[].filename')

# Check if CHANGELOG.md was modified
if echo "$CHANGED_FILES" | grep -q "CHANGELOG.md"; then
  echo -e "\033[0;32mChangelog exists\033[0m"
  echo "CHANGELOG_EXISTS=true" >> $GITHUB_ENV
else
  echo -e "\033[0;31mChangelog is missing\033[0m"
  echo "CHANGELOG_EXISTS=false" >> $GITHUB_ENV

  # Post a comment on the PR mentioning the author
COMMENT_BODY="@${AUTHOR} Please add an entry to \`CHANGELOG.md\` summarising the changes introduced in this PR."
COMMENT_JSON=$(jq -n --arg body "$COMMENT_BODY" '{body: $body}')

  curl -s -H "Authorization: token $GITHUB_TOKEN" \
       -H "Content-Type: application/json" \
       -X POST \
       -d "$COMMENT_JSON" \
       "https://api.github.com/repos/$GITHUB_REPOSITORY/issues/$PR_NUMBER/comments"

  exit 1
fi