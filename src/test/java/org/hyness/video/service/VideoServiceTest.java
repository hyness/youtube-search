package org.hyness.video.service;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hyness.video.service.VideoService.VideoDefinition.ANY;
import static org.hyness.video.service.VideoService.VideoDefinition.HIGH;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;

import org.hyness.video.domain.Result;
import org.hyness.video.service.VideoService.VideoServiceProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.io.Resources;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class VideoServiceTest {
    @InjectMocks
	private VideoServiceRestImpl service;
	
	private RestTemplate template = new RestTemplate();
	
	private int maxResults = 10;
	
	private String searchUrl = "https://www.googleapis.com/youtube/v3/search?q={term}&type=video&videoDefinition={videoDefinition}"
	        + "&pageToken={pageToken}&maxResults={maxResults}&key={apiKey}&part=snippet";
	
	private String apiKey = randomAlphanumeric(20);
	
	private MockRestServiceServer mockServer;

	private String term = randomAlphabetic(30);
	
	private String searchResult;
	
	@Before
	public void init() throws IOException {
		mockServer = MockRestServiceServer.createServer(template);
		setField(service, "template", template);
		
		VideoServiceProperties properties = new VideoServiceProperties();
        properties.setMaxResults(maxResults);
		properties.setSearchUrl(searchUrl);
		properties.setApiKey(apiKey);
        setField(service, "properties", properties);
		searchResult = Resources.toString(Resources.getResource("search-result.json"), UTF_8);
	}
	
	@Test
	public void search() throws Exception {
		String expectedUrl = UriComponentsBuilder.fromHttpUrl(searchUrl).buildAndExpand(term, ANY, "", maxResults, apiKey).toString();
		mockServer.expect(requestTo(expectedUrl)).andRespond(withSuccess(searchResult, APPLICATION_JSON));
		Result result = service.search(term);
		mockServer.verify();
		
		log.debug("result: {}", result);
		assertThat(result).isNotNull();
		assertThat(result.getItems()).isNotNull().hasSize(10);
	}
	
	@Test
	public void searchWithVideoDefinition() throws Exception {
		String expectedUrl = UriComponentsBuilder.fromHttpUrl(searchUrl).buildAndExpand(term, HIGH, "", maxResults, apiKey).toString();
		mockServer.expect(requestTo(expectedUrl)).andRespond(withSuccess(searchResult, APPLICATION_JSON));
		Result result = service.search(term, HIGH);
		mockServer.verify();
		
		log.debug("result: {}", result);
		assertThat(result).isNotNull();
        assertThat(result.getItems()).isNotNull().hasSize(10);
	}
    
    @Test
    public void searchWithVideoDefinitionAndPage() throws Exception {
        String page = randomAlphanumeric(10);
        String expectedUrl = UriComponentsBuilder.fromHttpUrl(searchUrl).buildAndExpand(term, HIGH, page, maxResults, apiKey).toString();
        mockServer.expect(requestTo(expectedUrl)).andRespond(withSuccess(searchResult, APPLICATION_JSON));
        Result result = service.search(term, HIGH, page);
        mockServer.verify();
        
        log.debug("result: {}", result);
        assertThat(result).isNotNull();
        assertThat(result.getItems()).isNotNull().hasSize(10);
    }
}
