SUMMARY = "Expand rootfs to max on first boot"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://expand-rootfs.sh \
    file://expand-rootfs.service \
"

inherit systemd

SYSTEMD_SERVICE:${PN} = "expand-rootfs.service"

RDEPENDS:${PN} += "parted e2fsprogs-resize2fs util-linux-findmnt bash"

do_install() {
      install -d ${D}${bindir}
      install -m 0755 ${WORKDIR}/expand-rootfs.sh ${D}${bindir}/

      install -d ${D}${systemd_system_unitdir}
      install -m 0644 ${WORKDIR}/expand-rootfs.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += "${systemd_system_unitdir}/expand-rootfs.service"
