FROM maven:3.8.4-openjdk-17-slim

WORKDIR /backend

COPY ./backend .

RUN mvn clean install

RUN javac src/main/java/com/projetcloud/ProjetCloudApplication.java

CMD ["java", "main/java/com/projetcloud/ProjetCloudApplication"]