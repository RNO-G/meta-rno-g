SUMMARY = "GPS stuff for RNO-G RevN"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PR="r7"

SRC_URI = "file://10-ubx-setup.conf file://10-ubx-env.sh file://rno-g-gps-setup file://rno-g-record-gps file://rno-g-record-gps.service"

inherit systemd

SYSTEMD_SERVICE:${PN} = "rno-g-record-gps.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"



do_install() {

#setup script
         install -d ${D}/rno-g/bin
         install -m 0755 ${WORKDIR}/rno-g-gps-setup ${D}/rno-g/bin

#gpsd dropin
         install -d ${D}${sysconfdir}/systemd/system/gpsd.service.d
         install -m 0644 ${WORKDIR}/10-ubx-setup.conf ${D}${sysconfdir}/systemd/system/gpsd.service.d/10-init-ubx.conf

#profile.d dropin
         install -d ${D}${sysconfdir}/profile.d
         install -m 0644 ${WORKDIR}/10-ubx-env.sh ${D}${sysconfdir}/profile.d

# record service
         install -d ${D}${systemd_system_unitdir}
         install -m 0755 ${WORKDIR}/rno-g-record-gps ${D}/rno-g/bin
         install -m 0644 ${WORKDIR}/rno-g-record-gps.service ${D}${systemd_system_unitdir}


}

FILES:${PN} += "  /rno-g/bin/rno-g-gps-setup /rno-g/bin/rno-g-record-gps ${sysconfdir}/systemd/system/gpsd.service.d/10-ubx-setup.conf ${sysconfdir}/profile.d/10-ubx-env.sh "

RDEPENDS:${PN} +=" gpsd gps-utils gpioset-service bash zstd create-rno-g-dir-service  "
