SUMMARY = "Groups for hardware subsystems"
LICENSE = "MIT"

inherit useradd

USERADD_PACKAGES = "${PN}"

GROUPADD_PARAM:${PN} = "--system gpio --system spi --system i2c"

SRC_URI = "file://90-hw-groups.rules"

do_install() {
       install -d ${D}${sysconfdir}/udev/rules.d
       install ${WORKDIR}/90-hw-groups.rules ${D}${sysconfdir}/udev/rules.d
}
