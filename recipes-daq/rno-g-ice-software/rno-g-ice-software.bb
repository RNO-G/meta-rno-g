SUMMARY = "RNO-G DAQ"
HOMEPAGE = "https://rno-g.org"
LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
PV="1.0.0"
PR="r2"
COMPATIBLE_MACHINE = "rno-g-revn"

EXTRA_OEMAKE:append = " MACHINE='${MACHINE}'"

inherit systemd
SYSTEMD_SERVICE:${PN} = "rno-g-acq.service rno-g-copy.service rno-g-copy-meta.service rno-g-apply-calib@DEEP.timer rno-g-apply-calib@SURF.timer rno-g-apply-calib@SWEEP.timer "

SRC_URI = "git://github.com/rno-g/rno-g-ice-software.git;protocol=https;branch=didaq-support"
SRC_URI += " file://10-didaq-setup.conf file://20-dirs.conf "
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

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
    oe_runmake install service-install DESTDIR=${D} PREFIX=/rno-g

    # dropin for rno-g-acq
    install -d ${D}/etc/systemd/system/rno-g-acq.service.d/
    install ${WORKDIR}/10-didaq-setup.conf ${D}/etc/systemd/system/rno-g-acq.service.d
    install ${WORKDIR}/20-dirs.conf ${D}/etc/systemd/system/rno-g-acq.service.d

    # stuff we don't use in yocto
    rm -rf ${D}/data
    rm -rf ${D}/etc/polkit-1
    rm -rf ${D}/rno-g/run

    # fixup permissions
    chown root:root ${D}/rno-g/bin
}


DEPENDS = " libdidaq zlib libgpios librno-g rno-g-user libconfig systemd "

FILES:${PN}= " /rno-g/bin/* /rno-g/cfg/default/*  /rno-g/cfg/overrides.json /rno-g/cfg/var/calib_channel.state "
FILES:${PN}+= " /rno-g/var/calib_channel.state /etc/systemd/system/rno-g*"

