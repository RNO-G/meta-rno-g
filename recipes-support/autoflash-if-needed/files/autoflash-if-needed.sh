#! /bin/sh

EMMC_PAYLOAD=/opt/flasher/emmc.partup
EMMC_TARGET=/dev/mmcblk0

if [ -f ${EMMC_PAYLOAD} ] ;
then
  echo $EMMC_PAYLOAD exists, will flash
  partup install ${EMMC_PAYLOAD} ${EMMC_TARGET} || exit 1
  sync || exit 1
  echo "Copying complete!"
  rm ${EMMC_PAYLOAD} || exit 1
  echo  "Payload erased"
else
  echo $EMMC_PAYLOAD not there, not doing anything
fi
