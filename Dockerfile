FROM maven:alpine
MAINTAINER hyness <hyness@hyness.com>

EXPOSE 8080
VOLUME /config

COPY . /usr/src/youtube-search/
WORKDIR /usr/src/youtube-search/
RUN mvn package && mkdir -p /opt/youtube-search && mv target/youtube-search-*.jar /opt/youtube-search
WORKDIR /
CMD java -Djava.security.egd=file:/dev/urandom -jar /opt/youtube-search/youtube-search-*.jar 
