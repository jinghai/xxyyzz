#!/bin/sh
#
#
#Ipet项目编译打包

cur_dir=$(cd "$(dirname "$0")"; pwd)


ant -f /src/xxyyzz/project/IPet -Dkey.alias=ipet.alias -Dkey.store.password=xiaojinghai -Dkey.alias.password=xiaojinghai -Dkey.store=$cur_dir/ipet.keystore clean release


#/src/xxyyzz/project/IPet/bin/IPet-release.apk