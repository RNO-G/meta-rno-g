DESCRIPTION = "EeVanescent included packages"
LICENSE="MIT"


PR="r4"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup


RDEPENDS:${PN} = " \
git vim \
curl wget \
valgrind \
fftw sqlite3 gsl \
gpsd gps-utils \
sudo \
tmux screen minicom \
cpufrequtils \
util-linux util-linux-fstrim perf \
python3-numpy python3-smbus python3-cffi python3-pyserial python3-spidev \
u-boot-tools libubootenv  \
man-db jlink uhubctl \
nmap bmon \
cowsay fuck-vscode nano smem \
libgpios libgpios-examples \
lsof \
mosquitto-clients \
emmcparm sbdm-cli \
gpioset-service \
cockpit-ws cockpit-systemd cockpit-shell \
"

