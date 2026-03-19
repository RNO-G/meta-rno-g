
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://gpsd.rno-g"

do_install:append() {

  install -d ${D}${sysconfdir}/default
  install ${WORKDIR}/gpsd.rno-g ${D}${sysconfdir}/default/gpsd
}

