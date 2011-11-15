CLASSPATH = bin:lib/jade.jar:lib/json/json_simple-1.1.jar
DEST_DIR = bin

all: khepera

khepera:
	javac -cp $(CLASSPATH) -d $(DEST_DIR) src/*.java

run:
	java -cp $(CLASSPATH) Launch

clean:
	rm -rf $(DEST_DIR)/*
