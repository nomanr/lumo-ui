#!/bin/bash

set -e

git fetch --tags

LATEST_RELEASE_TAG=$(git describe --tags --match "v*" --abbrev=0 2>/dev/null || echo "")

if [[ -z "$LATEST_RELEASE_TAG" ]]; then
    START_COMMIT=$(git rev-list --max-parents=0 HEAD)
else
    START_COMMIT=$LATEST_RELEASE_TAG
fi

RELEASE_NOTES=$(git log --pretty=format:"- %s" "$START_COMMIT"..HEAD | grep -vE "^-\s*CI\s")
echo "$RELEASE_NOTES"
