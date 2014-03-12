#!/bin/sh
#
#
#发布开发/连调版本
cur_dir=$(cd "$(dirname "$0")"; pwd)
tomcat_home=/opt/apache-tomcat-7.0.52

mkdir -p /src
rm -rf /src/*
cd /src
git clone https://github.com/jinghai/xxyyzz
cd /src/xxyyzz/server
mvn clean package 

service tomcat stop

rm -rf $tomcat_home/webapps/server
rm -rf $tomcat_home/logs/*
rm -rf $tomcat_home/work/*

unzip -oq /src/xxyyzz/server/target/server.war -d $tomcat_home/webapps/server

sed -i "/^jdbc.username=/c\jdbc.username=admin" $tomcat_home/webapps/server/WEB-INF/classes/application.properties
sed -i "/^jdbc.password=/c\jdbc.password=itserver" $tomcat_home/webapps/server/WEB-INF/classes/application.properties

mkdir -p /home/data/files
rm -rm /home/data/files/*
ln -sf /home/data/files $tomcat_home/webapps/server

service tomcat start

cd ${cur_dir}
tail -f $tomcat_home/logs/catalina.out
echo "===========install ok==========="