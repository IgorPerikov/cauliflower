#!/bin/bash
../gradlew clean build
docker build -t igorperikov/cauliflower-notes:latest .
docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"
docker push igorperikov/cauliflower-notes:latest
