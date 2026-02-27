COMPATIBLE_MACHINE:rno-g-revn = "rno-g-revn"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://k3-am625-rno-g-revN.dts"


do_configure:append() {
      # Replace the destination path with the correct location for your architecture and board
      cp ${WORKDIR}/k3-am625-rno-g-revN.dts ${S}/arch/arm64/boot/dts/ti/
}


