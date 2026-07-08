SUMMARY = "Sysctl/modprobe tweaks for rno-g hardware"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"


SRC_URI =  " file://sysctl-99-rno-g-net.conf "
SRC_URI += " file://modprobe-spidev.conf "
SRC_URI += " file://journald-rno-g-storage.conf "
SRC_URI += " file://ldconfig-00-rno-g.conf"
SRC_URI += " file://profile-00-rno-g.sh"
SRC_URI += " file://logind-allow-linger.conf"
SRC_URI += " file://sudo-provide-rno-g"
SRC_URI += " file://rno-g-tweaks.vim"

do_install() {

     #module stuff
     install -d ${D}${sysconfdir}/modprobe.d/
     install -m 0644 ${WORKDIR}/modprobe-spidev.conf ${D}${sysconfdir}/modprobe.d/spidev.conf


     #net stuff
     install -d ${D}${sysconfdir}/sysctl.d/
     install -m 0644 ${WORKDIR}/sysctl-99-rno-g-net.conf ${D}${sysconfdir}/sysctl.d/99-rno-g-net.conf


     # Make sure storage is persistent
     install -d ${D}/${sysconfdir}/systemd/journald.conf.d/
     install -m 0644 ${WORKDIR}/journald-rno-g-storage.conf ${D}/${sysconfdir}/systemd/journald.conf.d/rno-g-storage.conf

     # Don't kill tmux with login
     install -d ${D}/${sysconfdir}/systemd/logind.conf.d/
     install -m 0644 ${WORKDIR}/logind-allow-linger.conf ${D}/${sysconfdir}/systemd/logind.conf.d/99-allow-linger.conf

     # correct path
     install -d ${D}/etc/profile.d/
     install -m 0644 ${WORKDIR}/profile-00-rno-g.sh ${D}/etc/profile.d/00-rno-g.sh

     # correct ldconfig
     install -d ${D}/etc/ld.so.conf.d/
     install -m 0644 ${WORKDIR}/ldconfig-00-rno-g.conf ${D}/etc/ld.so.conf.d/00-rno-g.conf

     # make rno-g tools available when using sudo
     install -d ${D}/etc/sudoers.d
     install -m 0644 ${WORKDIR}/sudo-provide-rno-g ${D}/etc/sudoers.d/provide-rno-g

     #.vim settings
     install -d ${D}${datadir}/vim/vimfiles/plugin
     install -m 0644 ${WORKDIR}/rno-g-tweaks.vim ${D}${datadir}/vim/vimfiles/plugin/rno-g-tweaks.vim


}


# Make sure systemd-logind gets configuration change
pkg_postinst:${PN}() {
    # Check if we are running on the live target (not during offline image construction)
    if [ -z "$D" ]; then
        if systemctl is-active --quiet systemd-logind.service; then
            systemctl try-restart systemd-logind.service || :
       fi
    fi
}

FILES:${PN} = " ${sysconfdir}/modprobe.d/spidev.conf "
FILES:${PN} += " ${sysconfdir}/sysctl.d/99-rno-g-net.conf "
FILES:${PN} += " ${sysconfdir}/systemd/journald.conf.d/rno-g-storage.conf "
FILES:${PN} += " ${sysconfdir}/systemd/logind.conf.d/99-allow-linger.conf "
FILES:${PN} += " ${sysconfdir}/profile.d/00-rno-g.sh "
FILES:${PN} += " ${sysconfdir}/ld.so.conf.d/00-rno-g.conf "
FILES:${PN} += " ${sysconfdir}/sudoers.d/provide-rno-g "
FILES:${PN} += " ${datadir}/vim/vimfiles/plugin/rno-g-tweaks.vim "
