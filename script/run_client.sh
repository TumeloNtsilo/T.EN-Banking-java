#!/bin/bash

echo "compiling and starting client..."

cd /home/tumelo_ntsilo/Github_work/T.EN-Banking-java
mvn compile exec:java -Dexec.mainClass="za.co.tumelo.client.Client"

sleep 2