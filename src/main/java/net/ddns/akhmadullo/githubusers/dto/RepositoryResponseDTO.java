package net.ddns.akhmadullo.githubusers.dto;

import net.ddns.akhmadullo.githubusers.model.Branch;

import java.util.List;

public class RepositoryResponseDTO {

    private String name;
    private String owner;
    private List<Branch> branches;

    public RepositoryResponseDTO() {
    }

    public RepositoryResponseDTO(String name, String owner, List<Branch> branches) {
        this.name = name;
        this.owner = owner;
        this.branches = branches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
