SUMMARY = "GNU Readline wrapper"
DESCRIPTION = "rlwrap is a small utility that uses the GNU Readline library to allow the editing of keyboard input for any command."
HOMEPAGE = "https://github.com/hanslub42/rlwrap"
SECTION = "console/utils"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "readline libptytty"

SRC_URI = "https://github.com/hanslub42/rlwrap/releases/download/v0.48/rlwrap-0.48.tar.gz"

SRC_URI[sha256sum] = "cdf69074a216a8284574dddd145dd046c904ad5330a616e0eed53c9043f2ecbc"

inherit autotools
