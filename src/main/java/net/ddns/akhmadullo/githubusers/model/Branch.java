package net.ddns.akhmadullo.githubusers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {
    private String name;

    @JsonProperty("commit")
    private Commit lastCommit;

    public Branch() {
    }

    public Branch(String name, Commit lastCommit) {
        this.name = name;
        this.lastCommit = lastCommit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastCommit() {
        return lastCommit.getCommitSha();
    }

    public void setLastCommit(Commit lastCommit) {
        this.lastCommit = lastCommit;
    }
}
