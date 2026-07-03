SUMMARY = "Misc scripts for RNO-G Rev N"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://set-station-number"

do_install() {
    install -d ${D}/rno-g/bin
    install -m 0755 ${WORKDIR}/set-station-number ${D}/rno-g/bin
}

FILES:${PN} += "/rno-g/bin/set-station-number"

RDEPENDS:${PN} += "/bin/bash"
