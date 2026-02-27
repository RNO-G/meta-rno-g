SUMMARY = "Create RNO-G user and set up properly"
DESCRIPTION = "Eventually this will add ssh keys, etc."
LICENSE = "MIT"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "-u 1000 -d /home/rno-g -m -s /bin/bash rno-g"


do_install() {
      # Create the home directory in the destination folder
      install -d ${D}/home/rno-g


      # Change ownership to the new user so they have the correct permissions
      chown -R rno-g:rno-g ${D}/home/rno-g
}


FILES:${PN} = "/home/rno-g"
