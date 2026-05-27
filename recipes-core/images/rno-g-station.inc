inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"

IMAGE_FEATURES:append = " package-management tools-debug tools-sdk dev-pkgs doc-pkgs"

IMAGE_INSTALL = " \
  packagegroup-machine-base \
  packagegroup-core-boot \
  packagegroup-hwtools \
  packagegroup-userland \
  packagegroup-eevanescent \
  packagegroup-userland-flashing \
  packagegroup-rno-g \
  chrony chronyc \
  expand-rootfs \
  "

IMAGE_INSTALL:append = "${@bb.utils.contains('IMAGE_FEATURES', 'debug-tweaks', '', ' lockroot', d)}"



