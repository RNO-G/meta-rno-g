SUMMARY = "RNO-G Station SD Card Image with flasher "
DESCRIPTION = "Same as rno-g-station but adds a flasher"

require rno-g-station.inc

do_rootfs[depends] += "rno-g-station:do_image_complete"

flasher_but_i_hardly_know_her() {

      install -d ${IMAGE_ROOTFS}/opt/flasher

      # Copy the base image's partup file from the build deploy directory.
      # We use -L to dereference the symlink and copy the actual file data.
      cp -L ${DEPLOY_DIR_IMAGE}/rno-g-station-emmc-${MACHINE}.rootfs.partup ${IMAGE_ROOTFS}/opt/flasher/emmc.partup
}


ROOTFS_POSTPROCESS_COMMAND += "flasher_but_i_hardly_know_her;"

IMAGE_ROOTFS_EXTRA_SPACE = "2097152"

IMAGE_BASENAME = "rno-g-station-sd"
IMAGE_INSTALL += " autoflash-if-needed"
