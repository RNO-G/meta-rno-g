LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI = "file://lsb_release"

do_install() {

  install -d ${D}${sysconfdir}
  echo "EeVanescent ${DISTRO_VERSION} (${VERSION_CODENAME})" > ${D}${sysconfdir}/eevanescent-version
  echo "Built from branch: ${METADATA_BRANCH}" >> ${D}${sysconfdir}/eevanescent-version
  echo "Revision: ${METADATA_REVISION}" >> ${D}${sysconfdir}/eevanescent-version
  echo "Target system: ${TARGET_SYS}" >> ${D}${sysconfdir}/eevanescent-version


  echo "VERSION=\"${DISTRO_VERSION}\"" > ${D}${sysconfdir}/os-release
  echo "NAME=\"EeVanescent\"" >> ${D}${sysconfdir}/os-release
  echo "ID=\"eevanescent\"" >> ${D}${sysconfdir}/os-release
  echo "PRETTY_NAME=\"The EeVanescent Distribution ${DISTRO_VERSION}\"" >> ${D}${sysconfdir}/os-release
  echo "ANSI_COLOR=\"1;36\"" >> ${D}${sysconfdir}/os-release
  echo "HOME_URL=\"https://rno-g.org\"" >> ${D}${sysconfdir}/os-release

   install -d ${D}${bindir}
   install -m 0755 ${WORKDIR}/lsb_release ${D}${bindir}/

}



RPROVIDES:${PN} = "os-release"
RREPLACES:${PN} = "os-release"
RCONFLICTS:${PN} = "os-release"
