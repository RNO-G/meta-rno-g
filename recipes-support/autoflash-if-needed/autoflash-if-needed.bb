SUMMARY = "Auto-flash emmc image if it exists"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
file://autoflash-if-needed.sh \
file://autoflash-if-needed.service \
"

inherit systemd

SYSTEMD_SERVICE:${PN} = "autoflash-if-needed.service"

do_install() {

      install -d ${D}${bindir}
      install -m 0755 ${WORKDIR}/autoflash-if-needed.sh ${D}${bindir}/

      install -d ${D}${systemd_system_unitdir}
      install -m 0644 ${WORKDIR}/autoflash-if-needed.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "${systemd_system_unitdir}/autoflash-if-needed.service"
