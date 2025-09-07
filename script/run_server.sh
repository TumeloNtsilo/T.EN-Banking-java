#!/bin/bash


#compile the project using maven
echo "compiling..."
cd /home/tumelo_ntsilo/Github_work/T.EN-Banking-java
mvn compile

#I will then package later..


#Starting the server
# -cp (close path) - It tells Java where to look for compiled classes.
# target/class - Essentially, it tells Java: “Look here for the classes I want to run.”
#& tells java to run the server in the background
echo "Server starting"
java -cp target/classes za/co/tumelo/server/Server &

#Give the server 2 seconds to start
sleep 5

