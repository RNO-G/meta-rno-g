SUMMARY = "Custom udev rules for rno-g hardware"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "file://99-rno-g-uarts.rules file://controller-console"

do_install() {
      # Create the target directory
         install -d ${D}${sysconfdir}/udev/rules.d
         install -m 0644 ${WORKDIR}/99-rno-g-uarts.rules ${D}${sysconfdir}/udev/rules.d/

         install -d ${D}${bindir}
         install -m 0755 ${WORKDIR}/controller-console ${D}${bindir}
}

FILES:${PN} += "${sysconfdir}/udev/rules.d/99-rno-g-uarts.rules ${bindir}/controller-console"
