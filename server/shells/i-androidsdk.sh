#!/bin/sh
#
#
#安装JDK

echo "正在安装Android SDK..."
if [ ! -f "/download/adt-bundle-linux-x86_64-20131030.zip" ]; then
    wget -P /download --no-cookies --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com" "http://dl.google.com/android/adt/adt-bundle-linux-x86_64-20131030.zip"
fi
if [ ! -d "/opt/" ]; then
    mkdir -p /opt
fi
tar zxf /download/adt-bundle-linux-x86_64-20131030.zip -C /opt
unzip  /download/adt-bundle-linux-x86_64-20131030.zip -d /opt

Android_SDK_Home=/opt/adt-bundle-linux-x86_64-20131030


/opt/adt-bundle-linux-x86_64-20131030/sdk/tools/android list targets

#/opt/adt-bundle-linux-x86_64-20131030/sdk/tools/android list targets			#检查已安装的SDK版本
#/opt/adt-bundle-linux-x86_64-20131030/sdk/tools/android list sdk				#列出所有SDK
#/opt/adt-bundle-linux-x86_64-20131030/sdk/tools/android update sdk -t 1,2,4 -u 		#更新SDK
#ant -f /src/xxyyzz/project/IPet clean release

echo "===========Android SDK==========="