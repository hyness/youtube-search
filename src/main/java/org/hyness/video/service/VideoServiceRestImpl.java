/**
 * 
 */
package org.hyness.video.service;

import javax.inject.Inject;

import org.hyness.video.domain.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hy Goldsher
 */
@Service("videoService")
public class VideoServiceRestImpl implements VideoService {
	@Inject
	private RestTemplate template;
	
	@Value("${videoService.maxResults}")
	private int maxResults;
	
	@Value("${videoService.searchUrl}")
	private String searchUrl;
	
	@Value("${videoService.popularUrl}")
	private String popularUrl;

	@Override
	public Result search(String term) {
		return search(term, 1);
	}
	
	@Override
	public Result search(String term, int page) {
		int startIndex = getStartIndex(page);
		return template.getForObject(searchUrl, Result.class, term, startIndex, maxResults);
	}
	
	@Override
	public Result getMostPopular() {
		return getMostPopular(1);
	}
	
	@Override
	public Result getMostPopular(int page) {
		int startIndex = getStartIndex(page);
		return template.getForObject(popularUrl, Result.class, startIndex, maxResults);
	}

	private int getStartIndex(int page) {
		return ((page - 1) * maxResults) + 1;
	}
}
