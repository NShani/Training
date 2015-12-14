#!/bin/bash

echo "$(tput setaf 2)Enter the path for the domain file in Remote Host : your_username@remotehost.edu:foobar.txt $(tput sgr0)(e.g.:- appfactory@192.168.16.2:nadeeshani/certs/domain.crt)";
read remoteHostPath

echo "$(tput setaf 2)Enter the path to copy the domain file in Local Host : /some/local/directory$(tput sgr0) (e.g.:- /home/certs )"
read localPath

sudo scp $remoteHostPath $localPath

echo "$(tput setaf 2)Enter the Domain name :$(tput sgr0)(e.g.:- test.wso2.com) "
read domain

sudo mv $localPath/domain.crt $localPath/$domain

#copy domain file
sudo cp $localPath/$domain /usr/local/share/ca-certificates/$domain.crt
sudo cp $localPath/$domain /etc/ssl/certs/$domain.crt

# update certificcates
sudo update-ca-certificates

echo "$(tput setaf 2)Enter the Host Port :$(tput sgr0)(e.g. :- 5000) "
read hostPort

#update docker file
sudo sed -i '$ a DOCKER_OPTS="--insecure-registry '$domain:$hostPort'"' /etc/default/docker

#restart the docker service
sudo service docker stop
sudo service docker start

echo "$(tput setaf 2)Enter the IP of the Remote Host :"$(tput sgr0)"(e.g. :- 192.168.16.2) "
read remoteIP


# add host entry to domain
sudo sed -i "$ a $remoteIP $domain" /etc/hosts
echo "$(tput setaf 3)Updated the host entry$(tput sgr0)"
