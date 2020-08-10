FROM openjdk:8-jre-alpine
ADD target/recargas-0.0.1-SNAPSHOT.jar recargas.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/recargas.jar"]
EXPOSE 8092
