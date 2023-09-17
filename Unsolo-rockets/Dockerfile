FROM openjdk:17
EXPOSE 8080
ADD ./target/Unsolo-rockets-0.0.1-SNAPSHOT.jar Unsolo-rockets.jar
ENTRYPOINT ["java","-jar","Unsolo-rockets.jar"]

CMD java -cp target com.interswitch.Unsolorockets.UserServiceApplication