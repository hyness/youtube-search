/**
 * 
 */
package org.hyness.video.service;

import static com.google.common.base.Charsets.UTF_8;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;

import org.hyness.video.domain.Result;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.io.Resources;

/**
 * @author Hy Goldsher
 */
public class VideoServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private VideoService service;
	
	private RestTemplate template = new RestTemplate();
	
	private int maxResults = 10;
	
	private String searchUrl = "http://localhost/search?q={query}&start-index={startIndex}&max-results={maxResults}";
	
	private String popularUrl = "http://localhost/popular?q={query}&start-index={startIndex}&max-results={maxResults}";
	
	private MockRestServiceServer mockServer;

	private String term = randomAlphabetic(30);
	
	private String searchResult;
	
	@Before
	public void init() throws IOException {
		service = new VideoServiceRestImpl();
		mockServer = MockRestServiceServer.createServer(template);
		setField(service, "template", template);
		setField(service, "maxResults", maxResults);
		setField(service, "searchUrl", searchUrl);
		setField(service, "popularUrl", popularUrl);
		searchResult = Resources.toString(Resources.getResource("search-result.json"), UTF_8);
	}
	
	@Test
	public void search() throws Exception {
		String expectedUrl = UriComponentsBuilder.fromHttpUrl(searchUrl).buildAndExpand(term, 1, maxResults).toString();
		mockServer.expect(requestTo(expectedUrl)).andRespond(withSuccess(searchResult, APPLICATION_JSON));
		Result result = service.search(term);
		mockServer.verify();
		
		logger.debug("result: {}", result);
		assertThat(result).isNotNull();
		assertThat(result.getData()).isNotNull();
	}
	
	@Test
	public void searchWithPage() throws Exception {
		String expectedUrl = UriComponentsBuilder.fromHttpUrl(searchUrl).buildAndExpand(term, 41, maxResults).toString();
		mockServer.expect(requestTo(expectedUrl)).andRespond(withSuccess(searchResult, APPLICATION_JSON));
		Result result = service.search(term, 5);
		mockServer.verify();
		
		logger.debug("result: {}", result);
		assertThat(result).isNotNull();
		assertThat(result.getData()).isNotNull();
	}
	
	@Test
	public void searchWithPageAndHd() throws Exception {
		String expectedUrl = UriComponentsBuilder.fromHttpUrl(searchUrl).buildAndExpand(term, 41, maxResults).toString();
		mockServer.expect(requestTo(expectedUrl)).andRespond(withSuccess(searchResult, APPLICATION_JSON));
		Result result = service.search(term, 5);
		mockServer.verify();
		
		logger.debug("result: {}", result);
		assertThat(result).isNotNull();
		assertThat(result.getData()).isNotNull();
	}
}
