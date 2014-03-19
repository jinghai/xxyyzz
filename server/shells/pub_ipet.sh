#!/bin/sh
#
#
#Ipet项目编译打包

cur_dir=$(cd "$(dirname "$0")"; pwd)

file_path=/home/data/files/update/android

mkdir -p $file_path

ant -f /src/xxyyzz/project/IPet -Dkey.alias=ipet.alias -Dkey.store.password=xiaojinghai -Dkey.alias.password=xiaojinghai -Dkey.store=$cur_dir/ipet.keystore clean release


\cp -a /src/xxyyzz/project/IPet/bin/IPet-release.apk $file_path