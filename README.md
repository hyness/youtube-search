# youtube-search
[Spring Boot](http://projects.spring.io/spring-boot/) / [jQuery](https://jquery.com/) / [Bootstrap](https://getbootstrap.com/) app for searching for YouTube videos with the v3 api

[![Travis branch](https://img.shields.io/travis/hyness/youtube-search/master.svg?style=flat-square)](https://travis-ci.org/hyness/youtube-search)
[![Coverage Status](https://coveralls.io/repos/github/hyness/youtube-search/badge.svg?branch=develop)](https://coveralls.io/github/hyness/youtube-search?branch=master)

### Prerequisites
* [A free YouTube v3 API key](https://developers.google.com/youtube/registering_an_application#Create_API_Keys)
* [Maven](http://maven.apache.org)

#### Running the server with the [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/run-mojo.html)
```
[user@host ~]$ mvn -Drun.arguments="--key=MY_YOUTUBE_API_KEY" spring-boot:run
```

#### Building and running an executable jar
```
[user@host ~]$ mvn package
[user@host ~]$ java -jar target/youtube-search-*.jar --key=MY_YOUTUBE_API_KEY
```

#### Accessing the local server
http://localhost:8080

#### Remote server
http://video.hyness.org
