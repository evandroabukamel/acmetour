FROM azul/zulu-openjdk-alpine:11.0.10
WORKDIR /opt/app

ARG BUILD_DEV
# if --build-arg BUILD_DEV=1, set prod to 'dev' or set to null otherwise.
ENV PROFILE=${BUILD_DEV:+dev}
# if PROFILE is null, set it to 'prod' (or leave as is otherwise).
ENV PROFILE=${PROFILE:-prod}

ARG JAVA_FILE=target/tour-0.0.1-SNAPSHOT.jar
COPY ${JAVA_FILE} app.jar

ENTRYPOINT [ "java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar" ]
