SUMMARY = "RNO-G I/O libraries and calpulser control"
HOMEPAGE = "https://rno-g.org"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
PV="1.0.0"
PR="r3"
COMPATIBLE_MACHINE = "rno-g-revn"

SRC_URI = "git://github.com/rno-g/librno-g.git;protocol=https;branch=didaq"

SRCREV = "${AUTOREV}"
DEBIAN_NOAUTONAME:${PN} = "1"



EXTRA_OEMAKE:append = " MACHINE='${MACHINE}'"
SYSROOT_DIRS:append = " /rno-g "

TARGET_CFLAGS:prepend = " -I${RECIPE_SYSROOT}/rno-g/include "
TARGET_LDFLAGS:prepend = " -L${RECIPE_SYSROOT}/rno-g/lib "

S = "${WORKDIR}/git"

do_compile() {
    oe_runmake didaq rno-g-utils
}


do_install() {
  install -d ${D}/rno-g/bin
  install -d ${D}/rno-g/lib
  install -d ${D}/rno-g/include
  oe_runmake install install-didaq install-rno-g-utils DESTDIR=${D} PREFIX=/rno-g

# remove things we don't need on DIDAQ
  rm -f ${D}/rno-g/bin/rno-g-dump-ped
  rm -f ${D}/rno-g/bin/rno-g-wf-sample-diff-hists
  rm -f ${D}/rno-g/include/rno-g-nsample-diff-hist.h
}


DEPENDS = " libdidaq zlib libgpios  "
PACKAGES =+ " ${PN}-utils "
#RDEPENDS:${PN}-utils = " librno-g "

FILES:${PN}= " /rno-g/lib/librno-g.so.1 /rno-g/lib/librno-g.so.1.0.0 "
FILES:${PN}+= " /rno-g/lib/librno-g-didaq.so.1 /rno-g/lib/librno-g-didaq.so.1.0.0 "
FILES:${PN}+= " /rno-g/lib/librno-g-cal.so.1 /rno-g/lib/librno-g-cal.so.1.0.0 "
FILES:${PN}-dev = " /rno-g/lib/librno-g-cal.so /rno-g/lib/librno-g-didaq.so /rno-g/lib/librno-g.so /rno-g/include/rno-g.h /rno-g/include/rno-g-cal.h /rno-g/include/rno-g-didaq.h /rno-g/include/rno-g-version.h"
FILES:${PN}-utils = " /rno-g/bin/cal-cmd /rno-g/bin/rno-g-dump-hdr /rno-g/bin/rno-g-dump-wf /rno-g/bin/rno-g-dump-ds /rno-g/bin/rno-g-wf-stats "
