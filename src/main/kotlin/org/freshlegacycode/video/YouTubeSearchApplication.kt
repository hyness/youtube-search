package org.freshlegacycode.video

import com.fasterxml.jackson.annotation.JsonAlias
import org.freshlegacycode.video.VideoDefinition.ANY
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.runApplication
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import java.time.Instant
import javax.validation.constraints.NotBlank

@SpringBootApplication
@ConfigurationPropertiesScan
class YouTubeSearchApplication

fun main(args: Array<String>) {
    runApplication<YouTubeSearchApplication>(*args)
}

@Service
class YouTubeService(val properties: YouTubeProperties, val builder: WebClient.Builder, val webClient: WebClient = builder.build()) {
    fun search(term: String, videoDefinition: VideoDefinition? = ANY, pageToken: String? = null) =
        webClient.get()
            .uri(properties.searchUrl, term, videoDefinition, pageToken, properties.maxResults, properties.apiKey)
            .retrieve()
            .bodyToMono(Result::class.java)
}

@RestController
class SearchApi(val service: YouTubeService) {
    @GetMapping("/search/{term}")
    fun search(@PathVariable term: String) = service.search(term)

    @GetMapping("/search/{term}/{videoDefinition}")
    fun searchPage(@PathVariable term: String,
                   @PathVariable videoDefinition: VideoDefinition = ANY) = service.search(term, videoDefinition)

    @GetMapping("/search/{term}/{videoDefinition}/{pageToken}")
    fun searchPage(@PathVariable term: String,
                   @PathVariable videoDefinition: VideoDefinition = ANY,
                   @PathVariable pageToken: String = "") = service.search(term, videoDefinition, pageToken)
}

enum class VideoDefinition {
    ANY, STANDARD, HIGH
}

@Validated
@ConstructorBinding
@ConfigurationProperties("youtube-search")
data class YouTubeProperties(val maxResults: Int, @field:NotBlank val searchUrl: String, @field:NotBlank val apiKey: String)

data class Result(val etag: String, val prevPageToken: String?, val nextPageToken: String?, val pageInfo: PageInfo, val items: List<Item>)
data class PageInfo(val totalResults: Int, val resultsPerPage: Int)
data class Item(val etag: String, val id: Id, val snippet: Snippet)
data class Id(@JsonAlias("videoId") val id: String)
data class Snippet(val title: String, val description: String, val publishedAt: Instant)
