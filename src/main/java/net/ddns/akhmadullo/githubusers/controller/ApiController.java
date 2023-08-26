package net.ddns.akhmadullo.githubusers.controller;

import net.ddns.akhmadullo.githubusers.dto.RepositoryResponseDTO;
import net.ddns.akhmadullo.githubusers.exception.UnsupportedMediaTypeException;
import net.ddns.akhmadullo.githubusers.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/repositories")
    public ResponseEntity<List<RepositoryResponseDTO>> repositories(
            @RequestParam String username,
            @RequestHeader("Accept") String acceptHeader) {
        if (!"application/json".equals(acceptHeader)) {
            throw new UnsupportedMediaTypeException(acceptHeader);
        }

        List<RepositoryResponseDTO> repositories = gitHubService.getUserRepositories(username);

        return ResponseEntity.ok(repositories);
    }
}
