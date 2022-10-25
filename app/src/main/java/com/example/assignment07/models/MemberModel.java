package com.example.assignment07.models;

public class MemberModel {
    private String card_no;
    private String name;
    private String address;
    private String phone;
    private int unpaid_dues;

    public MemberModel() {
    }

    public MemberModel(String card_no, String name, String address, String phone, int unpaid_dues) {
        this.card_no = card_no;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.unpaid_dues = unpaid_dues;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
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

    public int getUnpaid_dues() {
        return unpaid_dues;
    }

    public void setUnpaid_dues(int unpaid_dues) {
        this.unpaid_dues = unpaid_dues;
    }
}
