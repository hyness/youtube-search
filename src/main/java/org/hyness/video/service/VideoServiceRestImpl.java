package org.hyness.video.service;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hyness.video.domain.Result;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("videoService")
@ConfigurationProperties("videoService")
public class VideoServiceRestImpl implements VideoService {
	@Inject
	private RestTemplate template;
	
	@Min(1)
	private int maxResults = 20;

	@NotNull
	private String searchUrl;
	
	@NotNull
	private String popularUrl;
	
	@Override
	public Result search(String term) {
		return search(term, false, 1);
	}
	
	@Override
	public Result search(String term, int page) {
		return search(term, false, page);
	}
	
	@Override
	public Result search(String term, boolean hd, int page) {
		return template.getForObject(getSearchUrl(hd), Result.class, term,
				getStartIndex(page), maxResults);
	}

	private String getSearchUrl(boolean hd) {
		return hd ? searchUrl + "&hd=true" : searchUrl;
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

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public void setPopularUrl(String popularUrl) {
		this.popularUrl = popularUrl;
	}
}
