echo 'Enter the name for the Image id of the registry'
read image_id

sudo docker inspect --format '{{ .NetworkSettings.IPAddress }}' $image_id
read ip

sudo docker run -d -e ENV_DOCKER_REGISTRY_HOST=$ip -e ENV_DOCKER_REGISTRY_PORT=5000 -e ENV_DOCKER_REGISTRY_USE_SSL=1 -p 8181:80 konradkleine/docker-registry-frontend:v2
