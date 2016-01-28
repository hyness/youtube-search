# youtube-search
[Spring Boot](http://projects.spring.io/spring-boot/) / [jQuery](https://jquery.com/) / [Bootstrap](https://getbootstrap.com/) app for searching for YouTube videos with the v3 api

[![Build Status](https://travis-ci.org/hyness/youtube-search.svg?branch=develop)](https://travis-ci.org/hyness/youtube-search)

### Prerequisites
* [A free YouTube v3 API key](https://developers.google.com/youtube/registering_an_application#Create_API_Keys)
* [Maven](http://maven.apache.org)

#### Running the server with the [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/run-mojo.html)
```
[user@host ~]$ mvn -Drun.arguments="--youtube-service.api-key=MY_SERVER_API_KEY" spring-boot:run
```

#### Building and running an executable jar
```
[user@host ~]$ mvn package
[user@host ~]$ java -jar target/youtube-search-*.jar --youtube-service.api-key=MY_SERVER_API_KEY
```

#### Accessing the local server
http://localhost:8080

#### Remote server
http://video.hyness.org
