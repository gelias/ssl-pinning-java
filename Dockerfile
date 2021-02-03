FROM openjdk:11-jre-slim

ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && \
    echo "$TZ" > /etc/timezone && \
    apt-get update && \
    apt-get install -y tzdata curl unzip && \
    rm /etc/localtime && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata && \
    apt-get clean

WORKDIR /app
COPY ./build/libs .

ENTRYPOINT ["java", "-jar", "ssl-pinning-1.0-SNAPSHOT.jar" ]
