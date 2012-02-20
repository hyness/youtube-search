/**
 * 
 */
package org.hyness.video.web;

import javax.inject.Inject;

import org.hyness.video.domain.Result;
import org.hyness.video.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Hy Goldsher
 */
@Controller
public class SearchController {
	@Inject
	private VideoService service;
	
	@ResponseBody
	@RequestMapping(value = "/search/{term}", method = RequestMethod.GET)
	public Result search(@PathVariable String term) {
		return service.search(term);
	}
}
