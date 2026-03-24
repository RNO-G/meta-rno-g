SUMMARY = "Locks down root user"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://00-lockroot.conf"

inherit extrausers

# make root unloginable
EXTRA_USERS_PARAMS += " \
    usermod -L root; \
    usermod -e 1 root; \
    usermod -s /sbin/nologin root; \
"


do_install:append() {
	install -d ${D}${sysconfdir}/ssh/sshd_config.d
	install  ${WORKDIR}/00-lockroot.conf ${D}${sysconfdir}/ssh/sshd_config.d
}

FILES:${PN} += "${sysconfdir}/ssh/sshd_config.d/00-lockroot.conf"

