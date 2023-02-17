FROM openjdk:17-alpine
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
CMD ["java","-jar", "build/libs/FrintSpark-1.0-SNAPSHOT-all.jar"]