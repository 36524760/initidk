FROM maven:3.9.6-amazoncorretto-21-debian as Build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline
RUN ls
COPY ./src ./src
RUN [ "mvn", "clean", "package", "-DskipTests" ]



# FROM amazoncorretto:21-alpine-jdk
FROM eclipse-temurin:21.0.2_13-jre-alpine
VOLUME /tmp

WORKDIR /opt/app
COPY --from=Build /app/target/apirest-0.0.1-SNAPSHOT.jar /opt/app/apirest.jar
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
EXPOSE 8080

ENTRYPOINT exec java $JAVA_OPTS -jar ./apirest.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar apirest.jar

# ENTRYPOINT ["java", "-jar", "java-api.jar"]
