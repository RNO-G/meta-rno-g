SUMMARY = "SD  Bind Mount for /data"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://mnt-sdcard.mount \
    file://sd-data-overlay.service \
    file://data.mount \
"

inherit systemd

# Tell systemd to enable all three units
SYSTEMD_SERVICE:${PN} = "mnt-sdcard.mount sd-data-overlay.service data.mount"

do_install() {
    install -d ${D}/data
    install -d ${D}/mnt/sdcard

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/mnt-sdcard.mount ${D}${systemd_system_unitdir}/
    install -m 0644 ${WORKDIR}/sd-data-overlay.service ${D}${systemd_system_unitdir}/
    install -m 0644 ${WORKDIR}/data.mount ${D}${systemd_system_unitdir}/
    touch ${D}/INTERNAL
}


FILES:${PN} += " \
    /data \
    /mnt/sdcard \
    ${systemd_system_unitdir}/mnt-sdcard.mount \
    ${systemd_system_unitdir}/data.mount \
    ${systemd_system_unitdir}/sd-data-overlay.service \
    /INTERNAL \
"
