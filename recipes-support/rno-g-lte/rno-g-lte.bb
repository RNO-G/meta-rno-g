SUMMARY = "LTE stuff for RNO-G Rev N"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR="r1"

SRC_URI = "file://10-wwan0.link file://90-lte.rules file://is-lte-up file://rno-g-lte-trigger file://rno-g-lted.service file://rno-g-lted.py file://setup-lte "

inherit systemd

SYSTEMD_SERVICE:${PN} = "rno-g-lted.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
      # Create the target directory for udev
         install -d ${D}${sysconfdir}/udev/rules.d
         install -m 0644 ${WORKDIR}/90-lte.rules ${D}${sysconfdir}/udev/rules.d/

         install -d ${D}/rno-g/bin
         install -m 0755 ${WORKDIR}/rno-g-lte-trigger ${D}/rno-g/bin
         install -m 0755 ${WORKDIR}/is-lte-up ${D}/rno-g/bin
         install -m 0755 ${WORKDIR}/setup-lte ${D}/rno-g/bin
         install -m 0755 ${WORKDIR}/rno-g-lted.py ${D}/rno-g/bin/rno-g-lted

         install -d ${D}${systemd_system_unitdir}
         install -m 0644 ${WORKDIR}/rno-g-lted.service ${D}${systemd_system_unitdir}

         install -d ${D}/${sysconfdir}/systemd/network
         install -m 0644 ${WORKDIR}/10-wwan0.link ${D}/${sysconfdir}/systemd/network
}

FILES:${PN} += "${sysconfdir}/udev/rules.d/90-lte.rules /rno-g/bin/is-lte-up /rno-g/bin/setup-lte /rno-g/bin/rno-g-lte-trigger /rno-g/bin/rno-g-lted ${systemd_system_unitdir}/rno-g-lted.service ${sysconfdir}/network/10-wwan0.link"

RDEPENDS:${PN} +=" python3-sdnotify "
