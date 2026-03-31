
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"


SRC_URI += " file://sudogroup"

do_install:append() {
  install -d 0710 ${D}${sysconfdir}/sudoers.d
  install -m 0440 ${WORKDIR}/sudogroup ${D}${sysconfdir}/sudoers.d/sudogroup
}


FILES:${PN}:append = " ${sysconfdir}/sudoers.d/sudogroup"

