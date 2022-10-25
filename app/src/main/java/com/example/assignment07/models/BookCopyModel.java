package com.example.assignment07.models;

public class BookCopyModel {

    private String book_id;
    private String branch_id;
    private String access_no;

    public BookCopyModel() {
    }

    public BookCopyModel(String book_id, String branch_id, String access_no) {
        this.book_id = book_id;
        this.branch_id = branch_id;
        this.access_no = access_no;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public String getAccess_no() {
        return access_no;
    }

    public void setAccess_no(String access_no) {
        this.access_no = access_no;
    }
}
