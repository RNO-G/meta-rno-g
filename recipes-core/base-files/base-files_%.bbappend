
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://issue "

do_install:append() {
    install -m 0644 ${WORKDIR}/issue ${D}${sysconfdir}/issue
    install -m 0644 ${WORKDIR}/issue ${D}${sysconfdir}/issue.net

    echo 'export PATH="$PATH:/rno-g/bin"' >> ${D}${sysconfdir}/profile.d/rno-g.conf

}

