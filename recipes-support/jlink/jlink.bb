
SUMMARY = "Segger J-Link Commander and SDK"

LICENSE = "CLOSED"

#LIC_FILES_CHKSUM = "file://Doc/LicenseIncGUI.txt;md5=1c58002b205eaa0d9413d9799665fca2" 

JLINK_NAME="JLink_Linux_V928_arm64"



SRC_URI = "https://www.segger.com/downloads/jlink/${JLINK_NAME}.tgz"
SRC_URI[sha256sum] = "46251dfe51a0369c03bebe81dc792aa1dfff567ea4b309037f75e27a287550d9"

S = "${WORKDIR}/${JLINK_NAME}"


# need to pass post parameter
do_fetch() {
	wget  --post-data "accept_license_agreement=accepted" ${SRC_URI} -O ${DL_DIR}/${JLINK_NAME}.tgz
}

do_install() {
      install -d ${D}${bindir}
#      install -d ${D}${libdir}
      cp  ${S}/JLinkGDBServer ${D}${bindir}
      cp  ${S}/JLinkExe ${D}${bindir}
#      cp  ${S}/arm/* ${D}${libdir}

			install -d ${D}/${sysconfdir}/udev/rules.d
      install ${S}/99-jlink.rules ${D}/${sysconfdir}/udev/rules.d

}

FILES:${PN} += "${sysconfdir}/udev/rules.d/99-jlink.rules"

INSANE_SKIP:${PN} += "ldflags"
INHIBIT_PACKAGE_STRIP = "1"
RDEPENDS:${PN} += "libusb1"
