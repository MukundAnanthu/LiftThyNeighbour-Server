compile:
	mvn clean compile

package:
	mvn package

install: compile
	mvn install

run: compile package
	java -jar target/neighbour-server-1.0.0.jar
