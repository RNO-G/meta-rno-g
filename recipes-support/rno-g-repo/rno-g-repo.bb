SUMMARY = "RNO-G Repository configuration"
LICENSE = "CLOSED"

SRC_URI = " \
file://rno-g.repo \
file://rno-g.mirrorlist \
"

inherit allarch

do_install() {
   # Install files to a staging directory to hide them from the host's DNF
   install -d ${D}${datadir}/rno-g-repo
   install -m 0644 ${WORKDIR}/rno-g.repo ${D}${datadir}/rno-g-repo/
   install -m 0644 ${WORKDIR}/rno-g.mirrorlist ${D}${datadir}/rno-g-repo/
}

# The '_ontarget' suffix ensures this ONLY runs on the device at first boot
pkg_postinst_ontarget:${PN} () {
      #!/bin/sh
      mkdir -p /etc/yum.repos.d
      cp /usr/share/rno-g-repo/rno-g.repo /etc/yum.repos.d/
      cp /usr/share/rno-g-repo/rno-g.mirrorlist /etc/yum.repos.d/
}


