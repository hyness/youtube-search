package org.freshlegacycode.video

import org.freshlegacycode.video.VideoDefinition.ANY
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders.LOCATION
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.time.Instant

@SpringBootTest(properties = ["key=test"])
class YouTubeSearchApplicationTest {
    @Test
    fun `context loads`() {
    }
}

@WebFluxTest(YouTubeSearchApi::class)
class YouTubeSearchApiTest {
    @Autowired
    lateinit var webClient: WebTestClient

    @MockBean
    lateinit var service: YouTubeSearchService

    @Test
    internal fun `executes a search and verifies the response`() {
        val now = Instant.now()
        val item = Item("item etag", Id("video id"), Snippet("title", "desc", now))
        val result = Result("etag", "p", "n", PageInfo(100, 10), listOf(item))
        given(service.search("test", ANY)).willReturn(Mono.just(result))

        webClient.get()
            .uri("/search/test")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody()
            .jsonPath("$.etag").isEqualTo("etag")
            .jsonPath("$.prevPageToken").isEqualTo("p")
            .jsonPath("$.nextPageToken").isEqualTo("n")
            .jsonPath("$.pageInfo.totalResults").isEqualTo(100)
            .jsonPath("$.pageInfo.resultsPerPage").isEqualTo(10)
            .jsonPath("$.items[0].etag").isEqualTo("item etag")
            .jsonPath("$.items[0].id.id").isEqualTo("video id")
            .jsonPath("$.items[0].snippet.title").isEqualTo("title")
            .jsonPath("$.items[0].snippet.description").isEqualTo("desc")
            .jsonPath("$.items[0].snippet.publishedAt").isEqualTo(now.toString())
    }
}

@SpringBootTest(webEnvironment = RANDOM_PORT, properties = ["key=test"])
class IndexRedirectRouterTest {
    @Autowired
    lateinit var webClient: WebTestClient

    @Test
    internal fun `requests root and gets redirected to index page`() {
        webClient.get()
            .uri("/")
            .exchange()
            .expectStatus().is3xxRedirection
            .expectHeader()
            .value(LOCATION, equalTo("/index.html"))
    }
}
