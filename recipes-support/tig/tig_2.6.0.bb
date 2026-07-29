SUMMARY = "Text-mode interface for Git"
HOMEPAGE = "https://github.io"
LICENSE = "GPL-2.0-only"
SECTION = "console/utils"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "ncurses git"


SRC_URI = "https://github.com/jonas/tig/releases/download/tig-${PV}/tig-${PV}.tar.gz"
SRC_URI[sha256sum] = "99d4a0fdd3d93547ebacfe511195cb92e4f75b91644c06293c067f401addeb3e"

inherit autotools-brokensep pkgconfig
