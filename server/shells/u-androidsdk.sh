#!/bin/sh
#
# 
# 
echo "正在卸载Android SDK..."

sed -i "/AUTO_Android_SDK/d" /etc/profile 
sleep 1
. /etc/profile
rm -rf /opt/android-sdk-linux

echo "===========卸载Android SDK ok===========" 