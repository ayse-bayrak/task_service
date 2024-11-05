FROM maven:3.6.3-jdk-11-openj9 as builder

WORKDIR usr/app
COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests
#this part is separate from belove part, above part is going to create one container some mvn container for me.
#and it's gonna copy my pom.xml and src folder inside of that container and then inside that container is gonna execute this command.
FROM adoptopenjdk/openjdk11:jre

COPY --from=builder usr/app/target/task-service-0.0.1-SNAPSHOT.jar /task-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "task-service-0.0.1-SNAPSHOT.jar"]

#first part is gonna create the jar file for me, and second part is gonna take it from the first one and then run it for me