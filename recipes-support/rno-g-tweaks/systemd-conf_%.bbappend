do_install:append() {
      # Remove poky logind drop-in file
      rm -f ${D}${systemd_unitdir}/logind.conf.d/00-logind.conf
}
