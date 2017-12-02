FROM openjdk:8u131-jre-alpine
COPY build/libs/cauliflower-auth-0.1.jar .
EXPOSE 8081
CMD ["java", "-Xmx1024m", "-Dspring.profiles.active=prod", "-jar", "cauliflower-auth-0.1.jar"]
