#!/bin/sh
#
#
#获取最新源码

mkdir -p /src
rm -rf /src/*
cd /src
git pull https://github.com/jinghai/xxyyzz master
