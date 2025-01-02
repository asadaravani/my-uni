#!/bin/bash

echo "Stopping existing application..."
pkill -f 'java -jar'

echo "Pulling latest changes from GitHub..."
git pull origin main

echo "Building the application..."
./mvnw clean install

echo "Starting the application..."
nohup java -jar target/MyUni-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
echo "Application deployed successfully!"
