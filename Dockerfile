FROM dockerfile/java:oracle-java8
ADD target/youtube-api-*.jar /opt/youtube-api/
EXPOSE 8080
WORKDIR /opt/youtube-api/
CMD java -Djava.security.egd=file:/dev/urandom -jar youtube-api-*.jar 