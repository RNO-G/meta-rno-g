
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://gpsd.rno-g"
PR="r1"

do_install:append() {

  install -d ${D}${sysconfdir}/default
  install ${WORKDIR}/gpsd.rno-g ${D}${sysconfdir}/default/gpsd
}

