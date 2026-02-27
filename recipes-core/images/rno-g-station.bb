SUMMARY = "RNO-G Station Image"
DESCRIPTION = "This goes on an RNO-G station"
LICENSE= "MIT"


inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"


IMAGE_INSTALL = " \
  packagegroup-machine-base \
  packagegroup-core-boot \
  packagegroup-hwtools \
  packagegroup-userland \
  packagegroup-benchmark \
  packagegroup-userland-flashing \
  packagegroup-cryptodev \
  packagegroup-3g \
  packagegroup-rno-g \
"


