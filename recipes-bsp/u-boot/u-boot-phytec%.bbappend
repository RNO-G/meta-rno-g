COMPATIBLE_MACHINE:rno-g-revn = "|rno-g-revn"

do_install:append() {
      cp -P ${D}/etc//u-boot-phytec-initial-env ${D}/etc/u-boot-initial-env
}
FILES:${PN}:append = " \
    /etc/u-boot-initial-env \
        "

