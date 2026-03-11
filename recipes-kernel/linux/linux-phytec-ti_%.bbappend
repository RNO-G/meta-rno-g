COMPATIBLE_MACHINE:rno-g-revn = "rno-g-revn"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += " file://k3-am625-rno-g-revN.dts"
SRC_URI += " file://moar_uarts.cfg"

do_configure:append() {

      cp ${WORKDIR}/k3-am625-rno-g-revN.dts ${S}/arch/arm64/boot/dts/ti/
}


