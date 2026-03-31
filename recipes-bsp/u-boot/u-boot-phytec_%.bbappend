COMPATIBLE_MACHINE:rno-g-revn = "|rno-g-revn"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
FILESEXTRAPATHS:prepend := "${THISDIR}/../device-trees/:"


SRC_URI += " file://k3-am625-rno-g-revN.dts "
SRC_URI += " file://k3-am625-rno-g-revN-u-boot.dtsi "
SRC_URI += " file://k3-am625-rno-g-binman.dtsi "
SRC_URI += " file://k3-am625-r5-rno-g.dts "
SRC_URI += " file://0001-rno-g-configs.patch"
SRC_URI += " file://0002-rno-g-early-boot.patch"

do_configure:prepend() {
  cp ${WORKDIR}/k3-am625-rno-g-revN.dts ${S}/dts/upstream/src/arm64/ti/
  cp ${WORKDIR}/k3-am625-r5-rno-g.dts ${S}/arch/arm/dts
  cp ${WORKDIR}/k3-am625-rno-g-revN-u-boot.dtsi ${S}/arch/arm/dts
  cp ${WORKDIR}/k3-am625-rno-g-binman.dtsi ${S}/arch/arm/dts
}

do_install:append() {
     cp -P ${D}/etc/u-boot-phytec-initial-env ${D}/etc/u-boot-initial-env
}

FILES:${PN}:append = " \
    /etc/u-boot-initial-env \
        "

