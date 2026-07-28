DESCRIPTION = "RNO-G specific packages"
LICENSE="MIT"


PR="r4"


PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = " \
  rno-g-user   \
  rno-g-serial  \
  rno-g-tweaks \
  rno-g-repo \
  rno-g-lte \
  rno-g-gps \
  rno-g-scripts \
  libdidaq libdidaq-examples \
  rno-g-loader \
  packagegroup-eevanescent \
  libconfig libconfig-dev \
"

