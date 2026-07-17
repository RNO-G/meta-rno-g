SUMMARY = "pydidaq library"
HOMEPAGE = "https://github.com/ejobe/pydidaq"
LICENSE = "CLOSED"
PR = "r0"
SRC_URI = "git://github.com/ejobe/pydidaq.git;protocol=https;branch=main"
SRCREV = "7fc6f3c2c8bf9cc65b40b63cea1db12899f00ec0"
DEBIAN_NOAUTONAME:${PN} = "1"

inherit python3targetconfig

S = "${WORKDIR}/git"

do_compile() {
}

RNO_G_SITE_PACKAGES="/rno-g/lib/python${PYTHON_BASEVERSION}/site-packages"


do_install() {

#binaries will go here
   install -d ${D}/rno-g/bin

#library scripts will go here, though they can be run as modules as well...
   install -d ${D}/${RNO_G_SITE_PACKAGES}/
   install -d ${D}/${RNO_G_SITE_PACKAGES}/didaq_serial
   install -m 0644 ${S}/didaq.py  ${D}/${RNO_G_SITE_PACKAGES}/
   install -m 0644 ${S}/didaq_serial/didaq_debug.py  ${D}/${RNO_G_SITE_PACKAGES}/didaq_serial
   install -m 0644 ${S}/didaq_adc_spi.py  ${D}/${RNO_G_SITE_PACKAGES}/

# debug  scripts will also go to site-packages and should be run with python3 -m X
# some of these are needed now until they get ported to SPI but that's fine...
   install -m 0644 ${S}/didaq_data_spi.py  ${D}/${RNO_G_SITE_PACKAGES}/
   install -m 0644 ${S}/didaq_serial/didaq_data.py  ${D}/${RNO_G_SITE_PACKAGES}/didaq_serial/
   install -m 0644 ${S}/didaq_serial/didaq_rf_trig.py  ${D}/${RNO_G_SITE_PACKAGES}/didaq_serial/
   install -m 0644 ${S}/didaq_serial/didaq_adc_config.py  ${D}/${RNO_G_SITE_PACKAGES}/didaq_serial/
   install -m 0644 ${S}/didaq_serial/didaq_i2c.py  ${D}/${RNO_G_SITE_PACKAGES}/didaq_serial/

#fixup hardcoded path in didaq_i2c, and put the header somewhere
  install -d ${D}/rno-g/share/didaq/config
  install -m 0644 ${S}/config/Si5338-didaq-rev2-Registers.h ${D}/rno-g/share/didaq/config
  sed -i 'config/Si5338|/rno-g/share/didaq/config/Si5338|' ${D}/${RNO_G_SITE_PACKAGES}/didaq_serial/didaq_i2c.py

# propgrams we will run regularly go to /rno-g/bin, get renamed and get a shebang added

   echo "#!/usr/bin/env python3" > ${D}/rno-g/bin/didaq-startup
   cat ${S}/startup_didaq.py  >>  ${D}/rno-g/bin/didaq-startup
   chmod 0755 ${D}/rno-g/bin/didaq-startup


   echo "#!/usr/bin/env python3" > ${D}/rno-g/bin/didaq-log-temps
   cat ${S}/log_temps.py  >>  ${D}/rno-g/bin/didaq-log-temps
   chmod 0755 ${D}/rno-g/bin/didaq-log-temps

   echo "#!/usr/bin/env python3" > ${D}/rno-g/bin/didaq-write-application-image
   cat ${S}/write_application_image.py  >> ${D}/rno-g/bin/didaq-write-application-image
   chmod 0755 ${D}/rno-g/bin/didaq-write-application-image


   echo "#!/usr/bin/env python3" > ${D}/rno-g/bin/didaq-serial-scan-threshold
   cat ${S}/didaq_serial/scan_threshold.py >> ${D}/rno-g/bin/didaq-serial-scan-threshold
   chmod 0755 ${D}/rno-g/bin/didaq-serial-scan-threshold

# firmware goes here, I guess?
  install -d  ${D}/rno-g/share/didaq/fw/
  install -m 0644 ${S}/fw/didaqfw_0xe3000030.rpd ${D}/rno-g/share/didaq/fw/20260625.rpd
}


RDEPENDS:${PN} = " rno-g-tweaks python3-spidev python3-pyserial "

PACKAGES =+ " ${PN}-serial ${PN}-fw ${PN}-utils ${PN}-serial-utils "

RDEPENDS:${PN} += " ${PN}-utils "
RDEPENDS:${PN}-utils  = " ${PN}-serial "

FILES:${PN} = " ${RNO_G_SITE_PACKAGES}/didaq.py ${RNO_G_SITE_PACKAGES}/didaq_adc_spi.py /rno-g/share/didaq/config/Si5338-didaq-rev2-Registers.h"
FILES:${PN}-serial = " ${RNO_G_SITE_PACKAGES}/didaq_serial/didaq_debug.py ${RNO_G_SITE_PACKAGES}/didaq_serial/didaq_adc_config.py ${RNO_G_SITE_PACKAGES}/didaq_serial/didaq_i2c.py "
FILES:${PN}-fw = "/rno-g/share/didaq/fw/20260625.rpd"
FILES:${PN}-utils = " ${RNO_G_SITE_PACKAGES}/didaq_data_spi.py  /rno-g/bin/didaq-startup /rno-g/bin/didaq-log-temps /rno-g/bin/didaq-write-application-image "
FILES:${PN}-serial-utils = "/rno-g/bin/didaq-serial-scan-threshold ${RNO_G_SITE_PACKAGES}/didaq_serial/didaq_data.py ${RNO_G_SITE_PACKAGES}/didaq_serial/didaq_rf_trig.py "
