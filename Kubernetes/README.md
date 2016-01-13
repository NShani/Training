# Kubernetes on Bare Metal (on ubuntu)

This is consists of a shell script.

KubeBareMetalConfig.sh
1. Clone the git repository for Kubernetes.
2. Download all the needed binaries.
3. Generate ssh key.
4. Stop if there are any running kube services.
5. Update configuration for the ubuntu cluster (cluster/ubuntu/config-default.sh)
6. Start kubernetes.
7. Deploy addons. (DNS and UI).
