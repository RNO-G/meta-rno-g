DESCRIPTION = "RNO-G specific packages"
LICENSE="MIT"


inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS:${PN} = " \
  rno-g-user   \
  rno-g-serial  \
  rno-g-tweaks \
  rno-g-repo \
  rno-g-lte \
  fftw sqlite3 gsl \
  libgpios libgpios-examples \
  libdidaq libdidaq-examples \
"
