SUMMARY = "Sysctl/modprobe tweaks for rno-g hardware"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI =  " file://99-rno-g-net.conf "
SRC_URI += " file://spidev.conf "
SRC_URI += " file://rno-g-storaged.conf "

do_install() {

     install -d ${D}${sysconfdir}/modprobe.d/
     install -m 0644 ${WORKDIR}/spidev.conf ${D}${sysconfdir}/modprobe.d/


     install -d ${D}${sysconfdir}/sysctl.d/
      install -m 0644 ${WORKDIR}/99-rno-g-net.conf ${D}${sysconfdir}/sysctl.d/


     # Make sure storage is persistent
      install -d ${D}/etc/systemd/journald.conf.d/
     install ${WORKDIR}/rno-g-storaged.conf ${D}/etc/systemd/journald.conf.d/
}


FILES:${PN} = " ${sysconfdir}/modprobe.d/spidev.conf "
FILES:${PN} += " ${sysconfdir}/sysctl.d/99-rno-g-net.conf "
FILES:${PN} += " ${sysconfdir}/systemd/journald.conf.d/rno-g-storaged.conf "
