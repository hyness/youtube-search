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
		return search(term, false, 1);
	}
	
	@Override
	public Result search(String term, int page) {
		return search(term, false, 1);
	}
	
	@Override
	public Result search(String term, boolean hd, int page) {
		return template.getForObject(searchUrl + (hd ? "&hd=true" :""), 
				Result.class, term, getStartIndex(page), maxResults);
	}
	
	@Override
	public Result getMostPopular() {
		return getMostPopular(1);
	}
	
	@Override
	public Result getMostPopular(int page) {
		return template.getForObject(popularUrl, Result.class, getStartIndex(page), maxResults);
	}

	private int getStartIndex(int page) {
		return ((page - 1) * maxResults) + 1;
	}
}
