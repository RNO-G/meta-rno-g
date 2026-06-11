DESCRIPTION = "RNO-G specific packages"
LICENSE="MIT"


PR="r3"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS:${PN} = " \
  rno-g-user   \
  rno-g-serial  \
  rno-g-tweaks \
  rno-g-repo \
  rno-g-lte \
  rno-g-gps \
  libdidaq libdidaq-examples \
  rno-g-loader \
  packagegroup-eevanescent \
"

