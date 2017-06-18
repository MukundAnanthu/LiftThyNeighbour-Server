compile:
	mvn clean compile

package:
	mvn package

install: compile
	mvn install
