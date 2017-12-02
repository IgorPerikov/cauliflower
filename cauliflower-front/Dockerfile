FROM openjdk:8u131-jre-alpine
COPY build/libs/cauliflower-front-0.1.jar .
EXPOSE 8082
CMD ["java", "-Xmx1024m", "-Dspring.profiles.active=prod", "-jar", "cauliflower-front-0.1.jar"]
