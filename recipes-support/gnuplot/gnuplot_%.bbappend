# Remove the strict X11 distribution check
REQUIRED_DISTRO_FEATURES:remove = "x11"

# Strip virtual/libx11 out of the build dependencies
DEPENDS:remove = "virtual/libx11"

# Turn off x11 and GUI components in the package configuration
PACKAGECONFIG:remove = "x11 qt5 qt6 cairo"

EXTRA_OECONF += "--without-x --disable-wxwidgets"

