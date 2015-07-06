package org.hyness.video.service;

import static com.google.common.base.Strings.nullToEmpty;
import static org.hyness.video.service.VideoService.VideoDefinition.ANY;

import javax.inject.Inject;

import org.hyness.video.domain.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("videoService")
public class VideoServiceRestImpl implements VideoService {
    @Inject
    private VideoServiceProperties properties;
    
    @Inject
    private RestTemplate template;
    
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
        return template.getForObject(properties.getSearchUrl(), Result.class, term,
                videoDefinition, nullToEmpty(pageToken), properties.getMaxResults(),
                properties.getApiKey());
    }
}
