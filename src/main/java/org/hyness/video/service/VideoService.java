/**
 * 
 */
package org.hyness.video.service;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.hyness.video.domain.Result;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

public interface VideoService {
    enum VideoDefinition {
        ANY, STANDARD, HIGH;
    }
    
	Result search(String term);
	Result search(String term, VideoDefinition videoDefinition);
	Result search(String term, VideoDefinition videoDefinition, String pageToken);
    
    @Getter
    @Setter
    @ConfigurationProperties("video-service")
    static class VideoServiceProperties {
        @Min(1) private int maxResults = 20;
        @NotEmpty private String searchUrl;
        @NotEmpty private String apiKey;
    }
}