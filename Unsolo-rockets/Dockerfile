FROM openjdk:17
# ADD target/Unsolo-rockets-0.0.1-SNAPSHOT.jar Unsolo-rockets.jar
ADD /home/runner/.m2/repository/com/interswitch/Unsolo-rockets/0.0.1-SNAPSHOT/Unsolo-rockets-0.0.1-SNAPSHOT.jar Unsolo-rockets.jar
ENTRYPOINT ["java","-jar","Unsolo-rockets.jar"]
EXPOSE 8080