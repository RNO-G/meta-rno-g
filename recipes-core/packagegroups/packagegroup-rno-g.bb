DESCRIPTION = "RNO-G specific packages"
LICENSE="MIT"


PR="r1"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS:${PN} = " \
  rno-g-user   \
  rno-g-serial  \
  rno-g-tweaks \
  rno-g-repo \
  rno-g-lte \
  libdidaq libdidaq-examples \
  rno-g-loader \
  packagegroup-eevanescent \
"

