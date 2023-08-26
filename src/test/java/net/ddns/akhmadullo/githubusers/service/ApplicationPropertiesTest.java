package net.ddns.akhmadullo.githubusers.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ApplicationPropertiesTest {

    @Value("${github.api.token}")
    private String githubToken;

    @Test
    public void testGithubTokenValue() {
        assertNotNull(githubToken, "GitHub API token value should not be null");
    }
}
