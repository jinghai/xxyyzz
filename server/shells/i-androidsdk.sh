#!/bin/sh
#
#
#安装JDK

yum install -y compat-libstdc++-296.i686
yum install -y compat-libstdc++-33.i686
yum install -y zlib.i686
yum install -y libstdc++.so.6 

echo "正在安装Android SDK..."
if [ ! -f "/download/adt-bundle-linux-x86_64-20131030.zip" ]; then
    wget -P /download --no-cookies --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com" "http://dl.google.com/android/adt/adt-bundle-linux-x86_64-20131030.zip"
fi
if [ ! -d "/opt/" ]; then
    mkdir -p /opt
fi
#tar zxf /download/adt-bundle-linux-x86_64-20131030.zip -C /opt
unzip  /download/adt-bundle-linux-x86_64-20131030.zip -d /opt

Android_SDK_Home=/opt/adt-bundle-linux-x86_64-20131030/sdk



grep -q "export AUTO_GEN_ANDROID" /etc/profile &&{
	echo "ANDROID config exits."
}||{
	echo "#AUTO_GEN_ANDROID" >>/etc/profile
	echo "export ANDROID_HOME=$Android_SDK_Home #AUTO_GEN_ANDROID" >>/etc/profile
}
sleep 1
. /etc/profile



/opt/adt-bundle-linux-x86_64-20131030/sdk/tools/android list targets
export ANDROID_HOME

#/opt/adt-bundle-linux-x86_64-20131030/sdk/tools/android list targets			#检查已安装的SDK版本
#/opt/adt-bundle-linux-x86_64-20131030/sdk/tools/android list sdk				#列出所有SDK
#/opt/adt-bundle-linux-x86_64-20131030/sdk/tools/android update sdk -t 1,2,4 -u 		#更新SDK
#ant -f /src/xxyyzz/project/IPet clean release

echo "===========Android SDK OK==========="