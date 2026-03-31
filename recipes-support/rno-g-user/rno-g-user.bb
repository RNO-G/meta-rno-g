SUMMARY = "Create RNO-G user and set up properly"
DESCRIPTION = "Creates rno-g user and adds necessary files"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS += " hw-groups "


RNO_G_REPOS = "librno-g rnog_gps rno-g-ice-software stationrc control-uC"
RNO_G_BRANCHES = "master master master main master"

inherit useradd

USERADD_PACKAGES = "${PN}"

RNO_G_PASSWORD_HASH= "\$y\$j9T\$Ww12xETBZp0ojQcBf2iRx1\$IbnSu7woCGB/m7etMKp9wBh8hXKHrlmhLM70DQyMF53"

USERADD_PARAM:${PN} = " -u 1000 -m -d /home/rno-g -s /bin/bash -p '${RNO_G_PASSWORD_HASH}' -G sudo,dialout,spi,i2c,gpio,adm rno-g"

SRC_URI = "file://authorized_keys"


# dynamically construct SRC_URI
python __anonymous() {

  repos = d.getVar("RNO_G_REPOS").split(' ')
  branches = d.getVar("RNO_G_BRANCHES").split(' ')
  src_uri = d.getVar("SRC_URI")
  src_rev_fmt = "_".join(repos)


  for i,repo in enumerate(repos):
    src_uri += " git://github.com/rno-g/" + repo + ";protocol=https;destsuffix=git/" + repo + ";branch=" + branches[i]

  d.setVar("SRC_URI", src_uri)
  d.setVar("SRCREV_FORMAT", src_rev_fmt)


}

SRCREV = "${AUTOREV}"


do_install() {
      install -m 0700 -o rno-g -g rno-g -d ${D}/home/rno-g/.ssh
      install -m 0700 -o rno-g -g rno-g ${WORKDIR}/authorized_keys ${D}/home/rno-g/.ssh

      for repo in ${RNO_G_REPOS};
      do
        install -d ${D}/home/rno-g/repos/${repo} ;
        cp -ar ${WORKDIR}/git/${repo}/. ${D}/home/rno-g/repos/${repo}/ ;

# remove references to yocto things
        git  -C ${D}/home/rno-g/repos/${repo} repack -ad
        rm -f ${D}/home/rno-g/repos/.git/objects/info/alternates

      done


      chown rno-g:rno-g -R ${D}/home/rno-g

# precreate .vscode-server with bad permissions
      install -m 000 -d ${D}/home/rno-g/.vscode-server

      echo N > ${D}/REV
}

FILES:${PN} = "/home/rno-g /home/rno-g/.ssh/authorized_keys /REV "
RDEPENDS:${PN} += "hw-groups bash perl"
