package org.hyness.video;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.hyness.video.service.YouTubeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = YouTubeApplication.class)
@WebIntegrationTest("server.port=0")
@ActiveProfiles("test")
public class YouTubeApplicationTest {
    @Inject
    private YouTubeService service;
    
    @Test
    public void contextLoads() throws Exception {
        log.debug("context loaded");
        assertNotNull(service);
    }
}
