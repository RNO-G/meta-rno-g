SUMMARY = " ASCII cartoon thing "
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"

SRCREV= "b67eda47925e8dee3a3fd4b513127a3f4ae15341"
SRC_URI="git://github.com/schacon/cowsay.git;protocol=https;branch=master  file://00-fixed.patch"

S="${WORKDIR}/git"

do_install() {

  install -d ${D}${bindir}
  install ${S}/cowsay ${D}${bindir}
  install -d ${D}${mandir}
  install ${S}/cowsay.1 ${D}${mandir}
  install -d ${D}${datadir}/cows
  install -t  ${D}${datadir}/cows/ ${S}/cows/*
}

FILES:${PN}+= " ${bindir}/cowsay ${datadir}/cows "
FILES:${PN}-doc += "${mandir}/cowsay.1"
RDEPENDS:${PN}+= "perl "
