FROM maven:3-amazoncorretto-17 AS build

WORKDIR /opt/app

COPY src ./src
COPY pom.xml .

RUN mvn -f pom.xml clean package install -e -X -DskipTests

FROM amazoncorretto:17

WORKDIR /opt/app

COPY --from=build /opt/app/target/ms*.jar app.jar

USER nobody

CMD [ "java", "-jar", "app.jar" ]