package net.ddns.akhmadullo.githubusers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

    @JsonProperty("sha")
    private String commitSha;

    public Commit() {
    }

    public Commit(String commitSha) {
        this.commitSha = commitSha;
    }

    public String getCommitSha() {
        return commitSha;
    }

    public void setCommitSha(String commitSha) {
        this.commitSha = commitSha;
    }
}
