#!/bin/bash

echo "compiling..."
cd /home/tumelo_ntsilo/Github_work/T.EN-Banking-java
mvn compile exec:java -Dexec.mainClass="za.co.tumelo.client.Client"


#Start client
echo
echo "Starting the client"
java -cp target/classes za/co/tumelo/client/Client

sleep 2