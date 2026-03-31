DESCRIPTION = "RNO-G specific packages"
LICENSE="MIT"

PACKAGE_ARG = "$MACHINE_ARCH"

inherit packagegroup

RDEPENDS:${PN} = " \
  rno-g-user   \
  rno-g-serial  \
  rno-g-tweaks \
  fftw sqlite3 gsl \
"
