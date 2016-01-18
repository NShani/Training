#!/bin/sh
echo "$(tput setaf 3)Have you already cloned the git repo for the kubernetes?(y/n)$(tput sgr0)" 
read isCloned

if [ $isCloned = "y" ]
	then echo "$(tput setaf 2)Enter the Path to Kubernetes folder :(/home/nadeeshani/Documents/Kube)$(tput sgr0) "
	read path
	cd $path
else
	echo "$(tput setaf 3)Cloning the Kubernetes repository...$(tput sgr0) "
	git clone https://github.com/GoogleCloudPlatform/kubernetes.git
	echo "$(tput setaf 3)Cloning finished.$(tput sgr0) "
fi

cd kubernetes

echo "$(tput setaf 2)Downloading the needed binaries...$(tput sgr0) "
sudo sh cluster/ubuntu/download-release.sh
echo "$(tput setaf 2)Binaries downloading finished.$(tput sgr0) "

echo "$(tput setaf 3)Downloading the prereq...$(tput sgr0) "
sudo apt-get install bridge-utils
echo "$(tput setaf 3)Downloading finished.$(tput sgr0) "

echo "$(tput setaf 2)Enter the User name :$(tput sgr0) "
read user

echo "$(tput setaf 2)Enter the IP adress of the machine :$(tput sgr0) "
read Ip

echo "$(tput setaf 3)Generating the ssh key$(tput sgr0) "
ssh-keygen -t rsa

cat id_rsa.pub >> authorized_keys

echo "$(tput setaf 3)Stopping the running kubernetes services $(tput sgr0) "
if((ps aux |grep /opt/bin/kube-controller-manager)>0)
then sudo stop kube-controller-manager
fi

if((ps aux |grep /opt/bin/kubelet)>0)
then sudo stop kubelet
fi

if((ps aux |grep /opt/bin/kube-scheduler)>0)
then sudo stop kube-scheduler
fi

if((ps aux |grep /opt/bin/kube-apiserver)>0)
then sudo stop kube-apiserver
fi

if((ps aux |grep /opt/bin/flanneld)>0)
then sudo stop flanneld
fi

if((ps aux |grep /opt/bin/etcd)>0)
then sudo stop etcd
fi

echo "$(tput setaf 3)Updating kubernetes configuration file...$(tput sgr0) "
sudo sed -i '21s/.*/export nodes=${nodes:-"'$user'@'$Ip'"}/' cluster/ubuntu/config-default.sh
sudo sed -i '24s/.*/role=${role:-"ai"}/' cluster/ubuntu/config-default.sh
sudo sed -i '30s/.*/export NUM_NODES=${NUM_NODES:-1}/' cluster/ubuntu/config-default.sh

sudo sed -i 's:KUBE_APISERVER_OPTS="\:KUBE_APISERVER_OPTS="\
 --runtime-config=extensions/v1beta1/deployments=true,extensions/v1beta1/daemonsets=true\:' cluster/ubuntu/util.sh

sudo sed -i '43s:.*:_nodes=$("${KUBE_ROOT}/cluster/kubectl.sh" get nodes) || true:g' cluster/validate-cluster.sh
sudo sed -i '44s/.*/found=$(($(echo "${_nodes}" | wc -l) - 1)) || true/g' cluster/validate-cluster.sh
sudo sed -i '45s/.*/ready=$(echo "${_nodes}" | grep -c "Ready") || true/g' cluster/validate-cluster.sh
sudo sed -i '202s:--logtostderr=true\\:--runtime-config=extensions/v1beta1/deployments=true,extensions/v1beta1/daemonsets=true\\\
 --logtostderr=true\\:g' kubernetes/cluster/ubuntu/util.sh
x=$(pwd)
export PATH=$x/cluster/ubuntu/binaries:$PATH

export KUBE_CONFIG_FILE=$x/cluster/ubuntu/config-default.sh
cd cluster

KUBERNETES_PROVIDER=ubuntu ./kube-up.sh

echo "$(tput setaf 2)Deploying the addons (UI and DNS)$(tput sgr0) "
cd ubuntu

export PATH=$x/cluster/ubuntu/binaries:$PATH
export KUBE_CONFIG_FILE=$x/cluster/ubuntu/config-default.sh
KUBERNETES_PROVIDER=ubuntu ./deployAddons.sh

echo "$(tput setaf 2)To get the cluster informations : $(tput sgr0) $(tput setaf 3)kubectl cluster-info $(tput sgr0)"
