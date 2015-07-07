package org.hyness.video.service;

import static com.google.common.base.Strings.nullToEmpty;
import static org.hyness.video.service.YouTubeService.VideoDefinition.ANY;

import javax.inject.Inject;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.hyness.video.domain.Result;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;

@Service("youtubeService")
public class YouTubeService {
    public enum VideoDefinition {
        ANY, STANDARD, HIGH;
    }
    
    @Inject
    private YouTubeProperties properties;
    
    @Inject
    private RestTemplate template;
    
    public Result search(String term) {
        return search(term, ANY);
    }
    
    public Result search(String term, VideoDefinition videoDefinition) {
        return search(term, videoDefinition, null);
    }
    
    public Result search(String term, VideoDefinition videoDefinition, String pageToken) {
        return template.getForObject(properties.searchUrl, Result.class, term,
                videoDefinition, nullToEmpty(pageToken), properties.maxResults,
                properties.apiKey);
    }
    
    @Getter
    @Setter
    @ConfigurationProperties("youtube-service")
    public static class YouTubeProperties {
        @Min(1) private int maxResults = 20;
        @NotEmpty private String searchUrl;
        @NotEmpty private String apiKey;
    }
}
