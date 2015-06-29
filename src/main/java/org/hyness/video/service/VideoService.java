/**
 * 
 */
package org.hyness.video.service;

import org.hyness.video.domain.Result;

public interface VideoService {
    enum VideoDefinition {
        ANY, STANDARD, HIGH;
    }
    
	Result search(String term);

	Result search(String term, VideoDefinition videoDefinition);

	Result search(String term, VideoDefinition videoDefinition, String pageToken);
}