#!/bin/bash
# Define paths
YOCTO_RPM_DIR="deploy-eevanescent/rpm"
FLAT_REPO_DIR="repo"
# Create the target flat directory
mkdir -p "${FLAT_REPO_DIR}"


echo "Making links"

# Find all RPMs in Yocto deploy dir and hardlink them into the flat folder
# (Hardlinks save disk space and are instant)
find "${YOCTO_RPM_DIR}" -name "*.rpm" -type f -exec ln -f -t "${FLAT_REPO_DIR}/" {} +

echo "Running creatrepo..."

# Generate a single unified package index using host tools
# (Requires 'createrepo_c' or 'createrepo' installed on host)
createrepo_c --update "${FLAT_REPO_DIR}"
rsync -avh -P repo/ rno-g.uchicago.edu:/repo/eevanescent/26/
