../gradlew clean build
docker build -t igorperikov/cauliflower-front:latest .
docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD"
docker push igorperikov/cauliflower-front:latest
