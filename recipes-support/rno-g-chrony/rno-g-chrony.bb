SUMMARY = "Custom monotonic boot configuration for Chrony"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR="r6"

SRC_URI += "file://97-nonvolatile.conf"
SRC_URI += "file://98-rno-g-ntp.conf"
SRC_URI += "file://99-gps.conf"


do_install() {
    install -d ${D}${sysconfdir}/chrony.d
    install -m 0644 ${WORKDIR}/97-monotonic.conf ${D}${sysconfdir}/chrony.d/
    install -m 0644 ${WORKDIR}/98-rno-g-ntp.conf ${D}${sysconfdir}/chrony.d/
    install -m 0644 ${WORKDIR}/99-gps.conf ${D}${sysconfdir}/chrony.d/

    install -d ${D}/data/chrony
    chmod 0750 ${D}/data/chrony


}


pkg_postinst_ontarget:${PN} () {

    # make sure we use dropins

    if [ -f /etc/chrony.conf ]; then
        if ! grep -q "include /etc/chrony.d/\*.conf" /etc/chrony.conf ; then
            echo "" >> /etc/chrony.conf
            echo "include /etc/chrony.d/*.conf" >> /etc/chrony.conf
        fi
    fi

    # restart chronyd
    if systemctl is-active --quiet chronyd; then
        systemctl restart chronyd
    fi
}

FILES:${PN} = "${sysconfdir}/chrony.d/97-monotonic.conf ${sysconfdir}/chrony.d/98-rno-g-ntp.conf  ${sysconfdir}/chrony.d/99-gps.conf /data/chrony"



