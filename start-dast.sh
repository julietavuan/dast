#!/bin/sh

# build dast app
cd dast/
mvn clean package
docker build -t dast-api-image .


cd ..

# build zap-attack app
cd zap-attack/
mvn clean package
docker build -t dast-api-image .


# start
docker-compose up
