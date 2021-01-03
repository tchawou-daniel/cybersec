FROM openjdk:8
ADD target/task-manager.jar task-manager.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "task-manager.jar"]