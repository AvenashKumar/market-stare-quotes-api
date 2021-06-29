FROM adoptopenjdk/openjdk16:alpine-slim

WORKDIR /apps/backend
COPY ./target/backend-marketstare-search-0.0.1-SNAPSHOT.jar /apps/backend/backend-marketstare-search.jar
RUN ln -snf /usr/share/zoneinfo/America/New_York /etc/localtime && echo "America/New_York" > /etc/timezone
ENTRYPOINT ["java", "-jar", "backend-marketstare-search.jar"]
EXPOSE 8080