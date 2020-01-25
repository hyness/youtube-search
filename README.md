# youtube-search
[Spring Boot](http://projects.spring.io/spring-boot/) / [jQuery](https://jquery.com/) / [Bootstrap](https://getbootstrap.com/) app for searching for YouTube videos with the v3 api

[![Travis branch](https://img.shields.io/travis/hyness/youtube-search/develop?logo=travis)](https://travis-ci.org/hyness/youtube-search)
[![Codecov Badge](https://img.shields.io/codecov/c/github/hyness/youtube-search/develop?logo=codecov)](https://codecov.io/gh/hyness/youtube-search/branch/develop)
[![Coverage Status](https://img.shields.io/coveralls/github/hyness/youtube-search/develop?logo=coveralls)](https://coveralls.io/github/hyness/youtube-search?branch=develop)
[![Codacy Badge](https://img.shields.io/codacy/grade/5cf21d9dc86b48d08b679e33eff9fa9d/develop?logo=codacy)](https://www.codacy.com/manual/hyness/youtube-search?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=hyness/youtube-search&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://img.shields.io/codacy/coverage/5cf21d9dc86b48d08b679e33eff9fa9d/develop?logo=codacy)](https://www.codacy.com/manual/hyness/youtube-search?utm_source=github.com&utm_medium=referral&utm_content=hyness/youtube-search&utm_campaign=Badge_Coverage)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=youtube-search&metric=alert_status)](https://sonarcloud.io/dashboard?id=youtube-search)

### Prerequisites
* [A free YouTube v3 API key](https://developers.google.com/youtube/registering_an_application#Create_API_Keys)
* [Gradle](https://gradle.org)

#### Running the server with the [Spring Boot Gradle Plugin](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/)
```
[user@host ~]$ KEY=MY_YOUTUBE_API_KEY gradle bootRun
```

#### Building and running an executable jar
```
[user@host ~]$ gradle build
[user@host ~]$ java -jar build/libs/youtube-search.jar --key=MY_YOUTUBE_API_KEY
```

#### Accessing the local server
http://localhost:8080

#### Remote server
http://video.hyness.org
