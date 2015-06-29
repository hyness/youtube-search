/**
 * 
 */
package org.hyness.video.service;

import org.hyness.video.domain.Result;
import org.hyness.video.domain.VideoDefinition;

public interface VideoService {
	Result search(String term);

	Result search(String term, VideoDefinition videoDefinition);

	Result search(String term, VideoDefinition videoDefinition, String pageToken);
}