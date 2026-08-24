SUMMARY = "Keep updating clock-eoch"
DESCRIPTION = "Make sure system time keeps going forward"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://systemd-update-epoch.esrvice file://systemd-update-epoch.timer"
inherit systemd

# Require systemd at runtime
RDEPENDS:${PN} = "systemd chrony"

# Automatically enable the timer service on target boot
SYSTEMD_SERVICE:${PN} = "systemd-update-epoch.timer"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_compile[noexec] = "1"

do_install() {
    install -d ${D}${libdir}
    touch ${D}${libdir}/clock-epoch

    install -d ${D}${systemd_system_unitdir}

    install -m 0644 ${WORKDIR}/systemd-update-epoch.service ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/systemd-update-epoch.timer ${D}${systemd_system_unitdir}

    chown root:root ${D}${libdir}/clock-epoch
}

pkg_postinst_ontarget:${PN} () {
    if systemctl is-active --quiet systemd; then
        systemctl daemon-reload
        systemctl enable --now systemd-epoch-ticker.timer
    fi
}

FILES:${PN} = " \
    ${libdir}/clock-epoch \
    ${systemd_system_unitdir}/systemd-update-epoch.service \
    ${systemd_system_unitdir}/systemd-update-epoch.timer \
"

