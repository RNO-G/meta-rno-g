SUMMARY = "didaq firmware"
HOMEPAGE = "https://github.com/ejobe/pydidaq"
LICENSE = "CLOSED"
PR = "r0"
SRC_URI = "git://github.com/ejobe/pydidaq.git;protocol=https;branch=main"
SRCREV = "27191e20381611994887d3a38dfe1b71ce29343b"
DEBIAN_NOAUTONAME:${PN} = "1"



do_compile() {
}


do_install() {

# firmware goes here, I guess?
  install -d  ${D}/rno-g/share/didaq/fw/
  install -m 0644 ${S}/fw/didaqfw_0xe3000030.rpd ${D}/rno-g/share/didaq/fw/20260625.rpd
}





FILES:${PN}= "/rno-g/share/didaq/fw/20260625.rpd"
