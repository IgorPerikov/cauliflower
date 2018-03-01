FROM openjdk:8u151-jre-alpine
COPY build/libs/cauliflower-auth-0.1.jar .
EXPOSE 8080
CMD ["java", "-Xmx1024m", "-Dspring.profiles.active=prod", "-jar", "cauliflower-auth-0.1.jar"]
