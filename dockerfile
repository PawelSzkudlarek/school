FROM openjdk:11-jre-slim-buster
ADD target/school-0.0.1-SNAPSHOT.jar .
EXPOSE 9090
CMD java -jar school-0.0.1-SNAPSHOT.jar