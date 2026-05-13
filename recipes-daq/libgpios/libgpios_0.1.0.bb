SUMMARY = "Thin convenience wrapper over the Linux gpio-v2 chardev interface"
HOMEPAGE = ""
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
PV="0.1.0+git"
SRC_URI = "git://github.com/cozzyd/libgpios.git;protocol=https;branch=main"

SRCREV = "5d2a750cc0aca9480e1e6b86f6994a425cb1a987"

S = "${WORKDIR}/git"

do_compile() {
    oe_runmake
}


do_install() {
    oe_runmake install DESTDIR=${D} PREFIX=${prefix}
}


PACKAGES =+ "${PN}-examples"

FILES:${PN}          = "${libdir}/libgpios.so.0.1.0 ${libdir}/libgpios.so.0"
FILES:${PN}-dev      = "${includedir}/libgpios.h ${libdir}/libgpios.so"
FILES:${PN}-examples = "${bindir}/gpios-get ${bindir}/gpios-set"
