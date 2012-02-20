/**
 * 
 */
package org.hyness.video.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.hyness.video.config.AppConfig;
import org.hyness.video.domain.Result;
import org.hyness.video.domain.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DelegatingSmartContextLoader;

/**
 * @author Hy Goldsher
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = DelegatingSmartContextLoader.class, classes = AppConfig.class)
public class VideoServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private VideoService service;
	
	@Test
	public void search() throws Exception {
		Result result = service.search("blake griffin");
		assertThat(result, notNullValue());
		for (Video video : result.getData().getItems()) {
			logger.debug("{}", video);
		}
	}
}
