SUMMARY = "RNO-G Station EMMC"
DESCRIPTION = "Image for RNO-G station EMMC"

require rno-g-station.inc

IMAGE_INSTALL += " sd-data-overlay"

IMAGE_BASENAME = "rno-g-station-emmc"

