SUMMARY = "Micron proprietary emmc tool"
LICENSE = "CLOSED"

# NOTE you need to procure the binary from https://www.micron.com/content/dam/micron/global/secure/products/software/emmcparm/emmcparm.zip and put it in files

SRC_URI = "file://emmcparm_arm_64bit"

S = "${WORKDIR}"

do_install() {
          install -d ${D}/${bindir}
          install -m 0755 ${WORKDIR}/emmcparm_arm_64bit ${D}/${bindir}/emmcparm
}

FILES:${PN}= "${bindir}/emmcparm "
