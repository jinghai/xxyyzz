#!/bin/sh
#
#
#安装JDK

echo "正在安装Android SDK..."
if [ ! -f "/download/android-sdk_r22.6-linux.tgz" ]; then
    wget -P /download --no-cookies --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com" "http://dl.google.com/android/android-sdk_r22.6-linux.tgz"
fi
if [ ! -d "/opt/" ]; then
    mkdir -p /opt
fi
tar zxf /download/android-sdk_r22.6-linux.tgz -C /opt

Android_SDK_Home=/opt/android-sdk-linux

grep -q "AUTO_Android_SDK" /etc/profile &&{
	echo "Android SDK config exits."
}||{
	echo "#AUTO_Android_SDK" >>/etc/profile
	echo "export PATH=\$PATH:\$Android_SDK_Home/tools #AUTO_Android_SDK" >>/etc/profile
}
sleep 1
. /etc/profile

android -h


#/opt/android-sdk-linux/tools/android list targets			#检查已安装的SDK版本
#/opt/android-sdk-linux/tools/android list sdk			#列出所有SDK
#/opt/android-sdk-linux/tools/android update sdk -t 1,2,4 -u 		#更新SDK

echo "===========Android SDK==========="