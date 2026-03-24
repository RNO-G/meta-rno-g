SUMMARY = "Sysctl/modprobe tweaks for rno-g hardware"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI =  " file://sysctl-99-rno-g-net.conf "
SRC_URI += " file://modprobe-spidev.conf "
SRC_URI += " file://journald-rno-g-storage.conf "
SRC_URI += " file://ldconfig-00-rno-g.conf"
SRC_URI += " file://profile-00-rno-g.sh"

do_install() {

     #module stuff
     install -d ${D}${sysconfdir}/modprobe.d/
     install -m 0644 ${WORKDIR}/modprobe-spidev.conf ${D}${sysconfdir}/modprobe.d/spidev.conf


     #net stuff
     install -d ${D}${sysconfdir}/sysctl.d/
     install -m 0644 ${WORKDIR}/sysctl-99-rno-g-net.conf ${D}${sysconfdir}/sysctl.d/99-rno-g-net.conf


     # Make sure storage is persistent
     install -d ${D}/etc/systemd/journald.conf.d/
     install -m 0644 ${WORKDIR}/journald-rno-g-storage.conf ${D}/etc/systemd/journald.conf.d/rno-g-storage.conf

     # correct path
     install -d ${D}/etc/profile.d/
     install -m 0644 ${WORKDIR}/profile-00-rno-g.sh ${D}/etc/profile.d/00-rno-g.sh

     # correct ldconfig
     install -d ${D}/etc/ld.so.conf.d/
     install -m 0644 ${WORKDIR}/ldconfig-00-rno-g.conf ${D}/etc/ld.so.conf.d/00-rno-g.conf
}


FILES:${PN} = " ${sysconfdir}/modprobe.d/spidev.conf "
FILES:${PN} += " ${sysconfdir}/sysctl.d/99-rno-g-net.conf "
FILES:${PN} += " ${sysconfdir}/systemd/journald.conf.d/rno-g-storage.conf "
FILES:${PN} += " ${sysconfdir}/profile.d/00-rno-g.sh "
FILES:${PN} += " ${sysconfdir}/ld.so.conf.d/00-rno-g.conf "
