SUMMARY = "Thin convenience wrapper over the Linux gpio-v2 chardev interface"
HOMEPAGE = ""
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
PR="r1"
SRC_URI = "git://github.com/cozzyd/libgpios.git;protocol=https;branch=main"

SRCREV = "af85379c10be15e61f45efaa64410839f3a1d96a"
DEBIAN_NOAUTONAME:${PN} = "1"

S = "${WORKDIR}/git"

do_compile() {
    oe_runmake
}


do_install() {
    oe_runmake install DESTDIR=${D} PREFIX=${prefix}
}


PACKAGES =+ "${PN}-examples"

FILES:${PN}          = "${libdir}/libgpios.so.0.1.1 ${libdir}/libgpios.so.0"
FILES:${PN}-dev      = "${includedir}/libgpios.h ${libdir}/libgpios.so"
FILES:${PN}-examples = "${bindir}/gpios-get ${bindir}/gpios-set"
