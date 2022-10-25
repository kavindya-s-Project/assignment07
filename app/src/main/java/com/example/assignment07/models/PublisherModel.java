package com.example.assignment07.models;

public class PublisherModel {
    private String name;
    private String address;
    private String phone;

    public PublisherModel() {
    }

    public PublisherModel(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public PublisherModel(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
