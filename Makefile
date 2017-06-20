compile:
	mvn clean compile

package: compile
	mvn package

install: compile
	mvn install

run: compile package
	java -jar target/neighbour-server-1.0.0.jar

build-docker:
	docker build -t "neighbour" -f Dockerfile .

build-alternate:
	docker build -t "neighbour2" -f Dockerfile2 .

run-docker: build-alternate
	bash run.sh
