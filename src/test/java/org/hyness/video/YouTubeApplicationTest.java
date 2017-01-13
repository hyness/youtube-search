package org.hyness.video;

import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

import javax.inject.Inject;

import org.hyness.video.service.YouTubeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@TestConfiguration
@SpringBootTest(webEnvironment = DEFINED_PORT)
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
