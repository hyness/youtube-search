# youtube-search
[Spring Boot](http://projects.spring.io/spring-boot/) / [jQuery](https://jquery.com/) / [Bootstrap](https://getbootstrap.com/) app for searching for YouTube videos with the v3 api

[![Travis branch](https://img.shields.io/travis/hyness/youtube-search/develop.svg?style=flat-square)](https://travis-ci.org/hyness/youtube-search)
[![Codecov Badge](https://img.shields.io/codecov/c/github/hyness/youtube-search)](https://codecov.io/gh/hyness/youtube-search/branch/develop)
[![Coverage Status](https://coveralls.io/repos/github/hyness/youtube-search/badge.svg?branch=develop)](https://coveralls.io/github/hyness/youtube-search?branch=develop)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5cf21d9dc86b48d08b679e33eff9fa9d)](https://www.codacy.com/manual/hyness/youtube-search?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=hyness/youtube-search&amp;utm_campaign=Badge_Grade)

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
