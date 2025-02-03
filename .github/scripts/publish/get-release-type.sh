#!/bin/bash

set -e  # Exit immediately on error

LABELS="$1"

if [ -z "$LABELS" ]; then
  echo "Error: No labels provided."
  exit 1
fi

if echo "$LABELS" | grep -q "release: major"; then
  RELEASE_TYPE="major"
elif echo "$LABELS" | grep -q "release: minor"; then
  RELEASE_TYPE="minor"
elif echo "$LABELS" | grep -q "release: patch"; then
  RELEASE_TYPE="patch"
else
  echo "No valid release label found."
  exit 1
fi

echo $RELEASE_TYPE

