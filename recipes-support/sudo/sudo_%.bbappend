
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"


SRC_URI += " file://sudogroup"

do_install:append() {
  install -d 0710 ${D}${sysconfdir}/sudoers.d
  install -m 0440 ${WORKDIR}/90-sudoers.cfg ${D}${sysconfdir}/sudoers.d
}


FILES:${PN}:append = " ${sysconfdir}/sudoers.d/sudogroup"

