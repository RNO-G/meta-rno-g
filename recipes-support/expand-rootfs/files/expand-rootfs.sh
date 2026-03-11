#!/bin/bash

# Dynamically find the root partition and expand it to 100% of the disk

ROOT_PART=$(findmnt -n -o SOURCE /)

# There has to be a better way to do this, on? Whatever.
ROOT_DEV=$(echo "$ROOT_PART" | sed -E 's/p?[0-9]+$//')
PART_NUM=$(echo "$ROOT_PART" | grep -oE '[0-9]+$')

echo "I am partition $PART_NUM on device $ROOT_DEV, let me spread my wings..."
parted -s "$ROOT_DEV" resizepart "$PART_NUM" 100% || exit 1
resize2fs "$ROOT_PART" || exit 1
echo "Filesystem expansion complete!"

systemctl disable expand-rootfs.service
