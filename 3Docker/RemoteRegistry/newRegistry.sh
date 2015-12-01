#!/bin/bash
CURRENT_DIR=`pwd`

#build new directory
mkdir -p certs 
SOURCE_DIR=$CURRENT_DIR/certs

#common name should be test.wso2.com.crt
# get certificate 
openssl req -newkey rsa:4096 -nodes -sha256 -keyout certs/domain.key -x509 -days 365 -out certs/domain.crt
echo 'Successfully created a certificate'

#run the docker registry with TLS enabled
sudo docker run -d -p 5000:5000 --restart=always --name registry -v `pwd`/certs:/certs -e REGISTRY_HTTP_TLS_CERTIFICATE=/certs/domain.crt -e REGISTRY_HTTP_TLS_KEY=/certs/domain.key registry:2



