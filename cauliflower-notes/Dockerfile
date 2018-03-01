FROM openjdk:8u151-jre-alpine
COPY build/libs/cauliflower-notes-0.1.jar .
EXPOSE 8080
CMD ["java", "-Xmx1024m", "-Dspring.profiles.active=prod", "-jar", "cauliflower-notes-0.1.jar"]
