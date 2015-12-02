# RemoteDockerRegistry

This is consists of two shell scripts

    1.) Build a Docker Registry (newRegistry.sh)
Create new certificate for the given domain name
Map the container port and the Host port for the Registry
Run the docker registry with TLS enabled

    2.) Configure the client to loggin to the remote Registry (remoteRegistryConfig.sh)
Copy the domain certificate from remote host to local host
Update the ca-certificates
Update the Docker with insecure-registry
Add a host entry for the new domain of the registry
