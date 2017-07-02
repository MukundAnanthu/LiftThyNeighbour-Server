#!/bin/bash
export PASS=waetiakahwoph6re9zeeZeish4eip3ae
export HOST=mysql

SERVER_DATA_DIR="${PWD}/serverdata"
mkdir -p "${SERVER_DATA_DIR}"

docker run -d \
--name=neighbourdb \
-e MYSQL_ROOT_PASSWORD=$PASS \
-v ${PWD}/data:/docker-entrypoint-initdb.d/ \
-v ${SERVER_DATA_DIR}:/var/lib/mysql \
mysql

docker run -d \
-e DBHOST=$HOST \
-e DBPORT=3306 \
-e DATABASE=application \
-e DBUSER=root \
-e DBPASSWORD=$PASS \
-p 8080:8080 \
--link neighbourdb:mysql neighbour2
