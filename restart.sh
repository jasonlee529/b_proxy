#!/bin/bash
jarFile=b_proxy.jar
if [ ! -z "$(ps -ef|grep $jarFile |grep java)" ] ; then
  echo "close pid : $(ps -ef|grep $jarFile |grep 'java'|awk '{print $2}')"
  ps -ef|grep $jarFile|grep 'java'|awk '{print $2}'|xargs kill -9
else
 echo "no running pid"
fi
profile=dev
if [ ! -z "$1" ] ; then
    profile=$1
fi
echo $profile
port=30010
if [ ! -z "$2" ] ; then
    port=$2
fi

java -jar -Dspring.profiles.active=$profile -Dserver.port=$port $jarFile  > log.file 2>&1 &
