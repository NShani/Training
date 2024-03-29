#git clone https://github.com/GoogleCloudPlatform/kubernetes.git

cd kubernetes

sudo sh cluster/ubuntu/download-release.sh

sudo apt-get install bridge-utils
echo "$(tput setaf 3)PWD$(tput sgr0)" 
pwd
echo "$(tput setaf 3)PWD$(tput sgr0)"

#echo "$(tput setaf 2)Enter the Number of Nodes :$(tput sgr0) "
#read noOfNodes

echo "$(tput setaf 2)Enter the User name :$(tput sgr0) "
read user

echo "$(tput setaf 2)Enter the IP adress of the machine :$(tput sgr0) "
read Ip


ssh-keygen -t rsa

cat id_rsa.pub >> authorized_keys

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
then stop kube-apiserver
fi

if((ps aux |grep /opt/bin/flanneld)>0)
then sudo stop flanneld
fi

if((ps aux |grep /opt/bin/etcd)>0)
then sudo stop etcd
fi

sudo sed -i '21s/.*/export nodes=${nodes:-"'$user'@'$Ip'"}/' cluster/ubuntu/config-default.sh
sudo sed -i '24s/.*/role=${role:-"ai"}/' cluster/ubuntu/config-default.sh
sudo sed -i '30s/.*/export NUM_NODES=${NUM_NODES:-1}/' cluster/ubuntu/config-default.sh

sudo sed -i '43s:.*:_nodes=$("${KUBE_ROOT}/cluster/kubectl.sh" get nodes) || true:g' cluster/validate-cluster.sh
sudo sed -i '44s/.*/found=$(($(echo "${_nodes}" | wc -l) - 1)) || true/g' cluster/validate-cluster.sh
sudo sed -i '45s/.*/ready=$(echo "${_nodes}" | grep -c "Ready") || true/g' cluster/validate-cluster.sh


cd cluster

echo "$(tput setaf 3)PWD$(tput sgr0)" 
pwd
echo "$(tput setaf 3)PWD$(tput sgr0)"

KUBERNETES_PROVIDER=ubuntu ./kube-up.sh





