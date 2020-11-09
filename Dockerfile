FROM openjdk:8-jre
COPY target/mall-0.0.1-SNAPSHOT.jar /data/app.jar
WORKDIR /data
ENV port 9508
ENV profile docker
CMD ["java", "-jar", "app.jar", "--server.port=${port}", "--spring.profiles.active=${profile}"]
