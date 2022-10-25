package com.example.assignment07.models;

public class BranchModel {
    private String id;
    private String branch_name;
    private String address;

    public BranchModel() {
    }

    public BranchModel(String id, String branch_name, String address) {
        this.id = id;
        this.branch_name = branch_name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
