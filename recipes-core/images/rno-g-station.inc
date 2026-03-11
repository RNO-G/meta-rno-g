inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"

IMAGE_FEATURES:append = " package-management tools-debug tools-sdk dev-pkgs"

IMAGE_INSTALL = " \
  packagegroup-machine-base \
  packagegroup-core-boot \
  packagegroup-hwtools \
  packagegroup-userland \
  packagegroup-userland-flashing \
  packagegroup-cryptodev \
  packagegroup-3g \
  packagegroup-rno-g \
  expand-rootfs \
"


