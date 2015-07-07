package org.hyness.video.web;

import javax.inject.Inject;

import org.hyness.video.domain.Result;
import org.hyness.video.service.YouTubeService.VideoDefinition;
import org.hyness.video.service.YouTubeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
	@Inject
	private YouTubeService service;
	
	@RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
	public Result search(@PathVariable String term) {
		return service.search(term);
	}
    
    @RequestMapping(value = "/search/{term}/{videoDefinition}", method = RequestMethod.GET)
    public Result searchPage(@PathVariable String term, @PathVariable VideoDefinition videoDefinition) {
        return service.search(term, videoDefinition);
    }
	
	@RequestMapping(value = "/search/{term}/{videoDefinition}/{pageToken}", method = RequestMethod.GET)
	public Result searchPage(@PathVariable String term, @PathVariable VideoDefinition videoDefinition, @PathVariable String pageToken) {
		return service.search(term, videoDefinition, pageToken);
	}
}
