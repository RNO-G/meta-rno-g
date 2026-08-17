SUMMARY = "RNO-G DAQ"
HOMEPAGE = "https://rno-g.org"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
PV="1.0.0"
PR="r0"

inherit useradd
USERADD_DEPENDS = "rno-g-user"

inherit systemd
SYSTEMD_SERVICE:${PN} = "rno-g-acq.service rno-g-copy.service rno-g-copy-meta.service rno-g-apply-calib@DEEP.timer rno-g-apply-calib@SURF.timer rno-g-apply-calib@SWEEP.timer "

SRC_URI = "git://github.com/rno-g/rno-g-ice-software.git;protocol=https;branch=didaq-support"
SRC_URI += " file://10-didaq-setup.conf" 
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

SRCREV = "${AUTOREV}"
DEBIAN_NOAUTONAME:${PN} = "1"

SYSROOT_DIRS:append = " /rno-g "

TARGET_CFLAGS:prepend = " -I${RECIPE_SYSROOT}/rno-g/include "
TARGET_LDFLAGS:prepend = " -L${RECIPE_SYSROOT}/rno-g/lib "

S = "${WORKDIR}/git"

do_compile() {
    oe_runmake 
}


do_install() {
    install -d ${D}/etc/systemd/system
    oe_runmake install service-install

    # dropin for rno-g-acq
    install -d ${D}/etc/systemd/system/rno-g-acq.service.d/
    install ${WORKDIR}/10-didaq-setup.conf /etc/systemd/system/rno-g-acq.service.d
}


DEPENDS = " libdidaq zlib libgpios librno-g "

FILES:${PN}= " /rno-g/bin/rno-g-acq /rno-g/bin/make-default-rno-g-cfg /rno-g/bin/check-rno-g-config /rno-g/bin/update-rno-g-config /rno-g/bin/rno-g-find-config" 
FILES:${PN}+=" /rno-g/bin/rno-g-simple-copy /rno-g/bin/rno-g-simple-copy-meta /rno-g/cfg/acq.cfg.default  /rno-g/cfg//rno-g/bin/apply_acq_overrides.py /rno-g/bin/rno-g-apply-calib"
FILES:${PN}+= " /etc/systemd/system/rno-g-acq.service /etc/systemd/system/rno-g-apply-calib@.service /etc/systemd/system/rno-g-apply-calib@DEEP.timer /etc/systemd/system/rno-g-apply-calib@SURF.timer /etc/systemd/system/rno-g-apply-calib@SWEEP.timer /etc/systemd/system/rno-g-apply-calib.target /etc/systemd/system/rno-g-copy-meta.service /etc/systemd/rno-g-copy.service "
FILES:${PN}+= "/etc/systemd/system/rno-g-acq.service.d/10-didaq-setup.conf "

