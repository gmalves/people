#
# Build stage
#
FROM maven:3.9.0 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:18
COPY --from=build /home/app/target/people-0.0.1-SNAPSHOT.jar /usr/local/lib/people.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/people.jar"]