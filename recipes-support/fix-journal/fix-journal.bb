SUMMARY = "Fix persistent journald logging"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# This package only contains an installation script, no binaries
ALLOW_EMPTY:${PN} = "1"
RDEPENDS:${PN} += "systemd"

pkg_postinst_ontarget:${PN}() {
#!/bin/sh
if [ -L /var/log ]; then
    echo "Fixing persistent storage"
    rm -f /var/log
    mkdir -p /var/log/journal
    chown -R root:systemd-journal /var/log
    chmod 2755 /var/log/journal
    systemctl restart systemd-journald
    journalctl --flush
    echo "Persistent storage migration complete"
  else
      echo "/var/log is already a directory. Skipping migration."
  fi
}
