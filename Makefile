DES = -d classes
CHE = -classpath classes
CC = javac
RUN = java -cp classes
DOC = javadoc -classpath src
JAR = jar cvfm
TST= java -jar test.jar

all: compAction doc tst jar runJar

doc:  
	@mkdir -p docs
	$(DOC) -subpackages action -d docs
	$(DOC) -subpackages poolOfResources -d docs

compAction:
	$(CC) $(DES) -classpath src src/**/*.java

jar: 	
	make compAction
tst:
	$(CC) -classpath test.jar test/test/*.java
	$(TST) test.*Test


help: 
	@echo "You can use :"
	@echo "compAction : compil all the .java of the project."
	@echo "doc : product the doc of the Action."
	@echo "tst : /!\ USE compAction BEFORE TST !! /!\ compile the test an launch all the test."
	@echo "help : help with the Make file."
	@echo "clean : Clean all the folders. (.class, GooseGame.jar, temp files, doc...)"

.PHONY: clean

clean:
	@rm -rf classes/* docs/* test/test/*.class *~ */*~ */*/*~ */*/*/*~ Action.jar
