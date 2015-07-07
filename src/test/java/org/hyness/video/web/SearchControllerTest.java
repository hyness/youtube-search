package org.hyness.video.web;

import static org.hyness.video.service.YouTubeService.VideoDefinition.HIGH;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.hyness.video.domain.Result;
import org.hyness.video.service.YouTubeService;
import org.hyness.video.service.YouTubeService.VideoDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {
	private static final String TERM = "foo";

	private static final VideoDefinition HD = HIGH;

	private static final String PAGE = "foo";

	@InjectMocks
	private SearchController controller;
	
	@Mock
	private YouTubeService service;

	private Result result = new Result();
	
	@Test
	public void searchWithValidResponse() throws Exception {
		when(service.search(TERM)).thenReturn(result);
		standaloneSetup(controller).build().perform(get("/search/{term}", TERM))
			.andExpect(status().isOk());
		verify(service).search(TERM);
	}

    @Test
    public void searchWithHdValidResponse() throws Exception {
        when(service.search(TERM, HD, PAGE)).thenReturn(new Result());
        standaloneSetup(controller).build().perform(get("/search/{term}/{hd}", TERM, HD, PAGE))
            .andExpect(status().isOk());
        verify(service).search(TERM, HD);
    }

	@Test
	public void searchWithHdAndPageValidResponse() throws Exception {
		when(service.search(TERM, HD, PAGE)).thenReturn(new Result());
		standaloneSetup(controller).build().perform(get("/search/{term}/{hd}/{page}", TERM, HD, PAGE))
			.andExpect(status().isOk());
		verify(service).search(TERM, HD, PAGE);
	}
}
