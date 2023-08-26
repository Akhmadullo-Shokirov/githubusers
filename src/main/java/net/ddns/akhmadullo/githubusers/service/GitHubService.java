package net.ddns.akhmadullo.githubusers.service;

import net.ddns.akhmadullo.githubusers.dto.RepositoryResponseDTO;
import net.ddns.akhmadullo.githubusers.exception.UserNotFoundException;
import net.ddns.akhmadullo.githubusers.model.Branch;
import net.ddns.akhmadullo.githubusers.model.Commit;
import net.ddns.akhmadullo.githubusers.model.RepositoryInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GitHubService {

    @Value("${github.api.base.url}")
    private String githubApiBaseUrl;

    @Value("${github.api.token}")
    private String githubToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<RepositoryResponseDTO> getUserRepositories(String username) {

        try {
            String url = githubApiBaseUrl + "/users/" + username + "/repos";

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            headers.set("Authorization", "Bearer " + githubToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<RepositoryInfo[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, RepositoryInfo[].class);
            RepositoryInfo[] repositories = response.getBody();
            if (repositories != null && repositories.length == 0) {
                return Collections.emptyList();
            }
            List<RepositoryResponseDTO> repositoriesWithBranches = new ArrayList<>();

            for (RepositoryInfo repository : repositories) {
                if (repository.isFork()) {
                    continue; // Skip forked repositories
                }

                RepositoryResponseDTO responseDTO = new RepositoryResponseDTO();
                responseDTO.setName(repository.getName());
                responseDTO.setOwner(repository.getOwner().getLogin());

                String branchesUrl = repository.getBranchesUrl().replace("{/branch}", "");
                ResponseEntity<Branch[]> branchesResponse = restTemplate.exchange(
                        branchesUrl, HttpMethod.GET, entity, Branch[].class);
                Branch[] branches = branchesResponse.getBody();
                List<Branch> branchList = new ArrayList<>();
                for (Branch branch : branches) {
                    branchList.add(new Branch(branch.getName(), new Commit(branch.getLastCommit())));
                }

                responseDTO.setBranches(branchList);

                repositoriesWithBranches.add(responseDTO);
            }

            return repositoriesWithBranches;
        }  catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException(username);
        }
    }
}
