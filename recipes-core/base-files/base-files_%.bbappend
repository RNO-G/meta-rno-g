
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://issue file://rno-g.repo file://rno-g.mirrorlist "

do_install:append() {
    install -m 0644 ${WORKDIR}/issue ${D}${sysconfdir}/issue
    install -m 0644 ${WORKDIR}/issue ${D}${sysconfdir}/issue.net

    install -m 0755 -d  ${D}${sysconfigdir}/yum.repos.d
    install -m 0644 ${WORKDIR}/rno-g.repo ${D}${sysconfigdir}/yum.repos.d/rno-g.repo
    install -m 0644 ${WORKDIR}/rno-g.mirrorlist ${D}${sysconfigdir}/yum.repos.d/rno-g.mirrorlist
}

