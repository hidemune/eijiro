#!/bin/bash

if [ $$ != `pgrep -fo $0`  ]; then
	#echo `basename $0` is already running.
	xdotool search --desktop 0 "eijiro" windowactivate
	sleep 1
	xte "key F5"
	exit 0
fi


CURDIR=`dirname $0`
echo $CURDIR

cd $CURDIR

java -jar $CURDIR/eijiro.jar

