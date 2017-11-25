../gradlew clean build
docker build -t igorperikov/cauliflower-auth:latest .
docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"
docker push igorperikov/cauliflower-auth:latest
