FROM gradle:jdk11
LABEL maintainer="hyness <hyness@freshlegacycode.org>"

ENV EXPOSE_PORT 8080
EXPOSE $EXPOSE_PORT
VOLUME /config

COPY . /usr/src/youtube-search/
WORKDIR /usr/src/youtube-search/
RUN gradle build && mkdir -p /opt/youtube-search && mv build/libs/youtube-search.jar /opt/youtube-search
WORKDIR /
ENTRYPOINT []
CMD java -Djava.security.egd=file:/dev/urandom -jar /opt/youtube-search/youtube-search.jar --server.port=${PORT=$EXPOSE_PORT}
