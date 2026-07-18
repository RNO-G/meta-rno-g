SUMMARY = "DIDAQ library"
HOMEPAGE = "https://github.com/rno-g/libdidaq"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
PR = "r0"
SRC_URI = "git://github.com/rno-g/libdidaq.git;protocol=https;branch=main"

SRCREV = "${AUTOREV}"
DEBIAN_NOAUTONAME:${PN} = "1"

S = "${WORKDIR}/git"

do_compile() {
    oe_runmake
}


do_install() {
   install -d ${D}/rno-g/bin
   install -d ${D}/rno-g/lib
   install -d ${D}/rno-g/include
   oe_runmake install DESTDIR=${D} PREFIX=/rno-g
}


PACKAGES =+ " ${PN}-examples "
DEPENDS = " libgpios "

FILES:${PN}          = "/rno-g/lib/libdidaq.so.0.0.4 /rno-g/lib/libdidaq.so.0"
FILES:${PN}-dev      = "/rno-g/include/didaq.h /rno-g/include/didaq_regs.h  /rno-g/include/didaq_adc.h /rno-g/lib/libdidaq.so"
FILES:${PN}-examples = "/rno-g/bin/didaq-dump /rno-g/bin/didaq-get-scalers /rno-g/bin/didaq-wfs /rno-g/bin/didaq-wfs-csv /rno-g/bin/didaq-coin-thresh-scan /rno-g/bin/didaq-beam-thresh-scan /rno-g/bin/didaq-adc-reg"
