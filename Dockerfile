FROM maven:alpine
MAINTAINER hyness <hyness@hyness.com>

ENV EXPOSE_PORT 8080
EXPOSE $EXPOSE_PORT
VOLUME /config

COPY . /usr/src/youtube-search/
WORKDIR /usr/src/youtube-search/
RUN mvn package && mkdir -p /opt/youtube-search && mv target/youtube-search-*.jar /opt/youtube-search
WORKDIR /
ENTRYPOINT []
CMD java -Djava.security.egd=file:/dev/urandom -jar /opt/youtube-search/youtube-search-*.jar --server.port=${PORT=$EXPOSE_PORT} 
