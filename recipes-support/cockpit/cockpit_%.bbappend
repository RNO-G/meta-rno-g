# Remove NetworkManager and package management dependencies
RDEPENDS:${PN}:remove = " \
    cockpit-networkmanager \
    packagekit \
    "
