
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"


SRC_URI += " file://90-sudoers.cfg"

do_install:append() {
  install -d ${D}${sysconfdir}/sudoers.d
  install -m 0600 ${WORKDIR}/90-sudoers.cfg ${D}${sysconfdir}/sudoers.d
}


FILES:${PN}:append = " ${sysconfdir}/sudoers.d/90-sudoers.cfg"

