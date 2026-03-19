SUMMARY = "Create RNO-G user and set up properly"
DESCRIPTION = "Creates rno-g user and adds necessary files"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit useradd

USERADD_PACKAGES = "${PN}"

RNO_G_PASSWORD_HASH= "\$y\$j9T\$Ww12xETBZp0ojQcBf2iRx1\$IbnSu7woCGB/m7etMKp9wBh8hXKHrlmhLM70DQyMF53"

USERADD_PARAM:${PN} = " -u 1000 -m -d /home/rno-g -s /bin/bash -p '${RNO_G_PASSWORD_HASH}' -G sudo,dialout,spi,i2c,gpio rno-g"

SRC_URI = "file://authorized_keys"


do_install() {
      install -m 0700 -o rno-g -g rno-g -d ${D}/home/rno-g/.ssh
      install -m 0700 -o rno-g -g rno-g ${WORKDIR}/authorized_keys ${D}/home/rno-g/.ssh
}

FILES:${PN} = "/home/rno-g /home/rno-g/.ssh/authorized_keys"
RDEPENDS:${PN} = " hw-rules "
