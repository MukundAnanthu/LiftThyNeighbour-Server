#!/bin/bash
export PASS=adminadmin
export HOST=mysql
docker run -d --name=neighbourdb -e MYSQL_ROOT_PASSWORD=$PASS -v ${PWD}/data:/docker-entrypoint-initdb.d/ mysql

docker run -d \
-e DBHOST=$HOST \
-e DBPORT=3306 \
-e DATABASE=application \
-e DBUSER=root \
-e DBPASSWORD=$PASS \
-p 8080:8080 \
--link neighbourdb:mysql neighbour2
