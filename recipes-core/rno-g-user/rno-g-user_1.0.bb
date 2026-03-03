SUMMARY = "Create RNO-G user and set up properly"
DESCRIPTION = "Eventually this will add ssh keys, etc."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit extrausers


RNO_G_PASSWORD_HASH= "\\$y\\$j9T\\$Ww12xETBZp0ojQcBf2iRx1$IbnSu7woCGB/m7etMKp9wBh8hXKHrlmhLM70DQyMF53"

EXTRA_USERS_PARAMS = "useradd -u 1000 -d /home/rno-g -s /bin/bash -p '${RNO_G_PASSWORD_HASH}' rno-g"

SRC_URI = "file://authorized_keys"

do_install() {
      # Create the home directory in the destination folder
      install -d ${D}/home/rno-g


      # Change ownership to the new user so they have the correct permissions
      chown -R rno-g:rno-g ${D}/home/rno-g

      # create ssh authorized_keys
      install -m 0700 -o rno-g -g rno-g -d ${D}/home/rno-g/.ssh
      install -m 0700 -o rno-g -g rno-g ${WORKDIR}/authorized_keys ${D}/home/rno-g/.ssh
}


FILES:${PN} = "/home/rno-g"
