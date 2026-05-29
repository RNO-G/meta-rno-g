SUMMARY = "Swissbit proprietary data manager"
LICENSE = "CLOSED"
PR="r1"

# NOTE you need to procure the binary from https://www.swissbit.com/files/private/data/SBDM/SBDM_Latest.zip

SRC_URI = "file://sbdm-cli"

S = "${WORKDIR}"

do_install() {
          install -d ${D}/${bindir}
          install -m 0755 ${WORKDIR}/sbdm-cli ${D}/${bindir}/
}

FILES:${PN}= "${bindir}/sbdm-cli "

RDEPENDS:${PN} += " fuse zlib "
INSANE_SKIP:${PN} += "already-stripped"
