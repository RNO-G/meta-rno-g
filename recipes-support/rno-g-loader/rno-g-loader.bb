SUMMARY = "RNO-G onboard firmware loader"
HOMEPAGE = "https://github.com/rno-g/control-uC"
LICENSE = "CLOSED"
SRC_URI = "git://github.com/rno-g/control-uC.git;protocol=https;branch=revn"

SRCREV = "853f1ce3346048bcee429ada0ba0a9c847fdecac"
DEBIAN_NOAUTONAME:${PN} = "1"

S = "${WORKDIR}/git/loader"

do_compile() {
    oe_runmake
}


do_install() {
   install -d ${D}/rno-g/bin
   oe_runmake install DESTDIR=${D} PREFIX=/rno-g/
}



FILES:${PN}= "/rno-g/bin/rno-g-controller-loader /rno-g/bin/rno-g-controller-extract"
