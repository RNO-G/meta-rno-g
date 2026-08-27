DESCRIPTION = "RNO-G specific packages"
LICENSE="MIT"


PR="r7"


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
  rno-g-chrony \
  libdidaq libdidaq-examples \
  rno-g-loader \
  packagegroup-eevanescent \
  libconfig libconfig-dev \
  librno-g librno-g-utils librno-g-dev \
  pydidaq pydidaq-utils pydidaq-serial pydidaq-serial-utils \
  rno-g-ice-software \
  \
"

