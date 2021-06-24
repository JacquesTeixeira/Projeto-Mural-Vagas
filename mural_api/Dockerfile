FROM openjdk:11.0.11-9-jdk

ADD target/mural_api-0.0.1-SNAPSHOT.war mural_api.war

ENTRYPOINT ["java", "-jar", "/mural_api.war"]

EXPOSE 8080
