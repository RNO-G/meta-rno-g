SUMMARY = "gpioset helpers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS:${PN} = "libgpiod-tools"
SRC_URI = " file://gpioset-low@.service file://gpioset-high@.service "

PR="r1"

do_install() {

  install -d ${D}${systemd_system_unitdir}
  install -m 0644 ${WORKDIR}/gpioset-low@.service ${D}${systemd_system_unitdir}/
  install -m 0644 ${WORKDIR}/gpioset-high@.service ${D}${systemd_system_unitdir}/
}

FILES:${PN} += " ${systemd_system_unitdir}/gpioset-low@.service ${systemd_system_unitdir}/gpioset-high@.service "


