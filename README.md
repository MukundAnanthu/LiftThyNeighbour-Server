# KnowThyNeighbour-Server
## Prerequisites
* Install [Docker](https://docs.docker.com/engine/installation/)

## To run
```
make build-docker
docker run -p 8080:8080 neighbour
```

## To delete unneeded docker stuff
```
docker ps -aq -f status=exited | xargs docker rm 
docker volume ls -qf dangling=true | xargs docker volume rm
docker images -aq -f dangling=true | xargs docker rmi
```
