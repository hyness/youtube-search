package org.hyness.video.service;

import static com.google.common.base.Strings.nullToEmpty;
import static org.hyness.video.service.VideoService.VideoDefinition.ANY;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hyness.video.domain.Result;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service("videoService")
@ConfigurationProperties("videoService")
public class VideoServiceRestImpl implements VideoService {
    @Inject
    private RestTemplate template;
    
    @Min(1)
    private int maxResults = 20;

    @NotNull
    private String searchUrl;
    
    @NotEmpty
    private String apiKey;
    
    @Override
    public Result search(String term) {
        return search(term, ANY);
    }
    
    @Override
    public Result search(String term, VideoDefinition videoDefinition) {
        return search(term, videoDefinition, null);
    }
    
    @Override
    public Result search(String term, VideoDefinition videoDefinition, String pageToken) {
        return template.getForObject(searchUrl, Result.class, term, videoDefinition, nullToEmpty(pageToken), maxResults, apiKey);
    }
}
