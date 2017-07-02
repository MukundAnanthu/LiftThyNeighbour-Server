# KnowThyNeighbour-Server
## Prerequisites
* Install [Docker](https://docs.docker.com/engine/installation/)

## To run
### One time setup
```
make build-docker
```

### To build code and start
```
make run-docker
```

## Docs
* Initial db seed data in `data/data.sql`
* We use docker to host our server
* The server docker `neighbour` is based from `openjdk:8-jdk`
* We bring up a `mysql` docker container seeded with the data at `data/data.sql`
* We link this container with a container of `neighbour` image
* To keep the mysql data persistent on every restart we link a folder to `/var/lib/mysql` in the mysql container
* `Dockerfile` is the Docker file of `neighbour` image
* `run.sh` starts the server
* We have hosted this server temporarily at `gridlock.pandel.in:8080` . If this is not accessible then most probably we have brought it down

## To delete docker stuff
```
docker ps -aq -f status=exited | xargs docker rm 
docker volume ls -qf dangling=true | xargs docker volume rm
docker images -aq -f dangling=true | xargs docker rmi
```

## Authors
* Ajith Pandel - [contact@pandel.in](mailto:contact@pandel.in) - [https://www.linkedin.com/in/ajithpandel](https://www.linkedin.com/in/ajithpandel)
* Mukund Ananthu - [mukund.ananthu@gmail.com](mailto:mukund.ananthu@gmail.com) - [https://www.linkedin.com/in/mukundananthu](https://www.linkedin.com/in/mukundananthu/)