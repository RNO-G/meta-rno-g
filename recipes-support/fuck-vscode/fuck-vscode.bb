SUMMARY = "Prevent VSCode from infecting machine"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit systemd

DEPENDS = "rno-g-user"
SRC_URI = "file://home-rno\x2dg-.vscode\x2dserver.mount"
SYSTEMD_SERVICE:${PN} = "home-rno\x2dg-.vscode\x2dserver.mount"


do_install() {
      install -d ${D}${systemd_unitdir}/system
      install -m 0644 ${WORKDIR}/home-rno\\x2dg-.vscode\\x2dserver.mount ${D}${systemd_unitdir}/system/
}

