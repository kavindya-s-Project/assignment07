package com.example.assignment07.models;

import java.util.Date;

public class LendingModel {

    private String access_no;
    private String branch_id;
    private String card_no;
    private Date out_date;
    private Date due_date;
    private Date returned_date;

    public LendingModel() {
    }

    public LendingModel(String access_no, String branch_id, String card_no, Date out_date,
                        Date due_date, Date returned_date) {
        this.access_no = access_no;
        this.branch_id = branch_id;
        this.card_no = card_no;
        this.out_date = out_date;
        this.due_date = due_date;
        this.returned_date = returned_date;
    }

    public String getAccess_no() {
        return access_no;
    }

    public void setAccess_no(String access_no) {
        this.access_no = access_no;
    }

    public String getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(String branch_id) {
        this.branch_id = branch_id;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public Date getOut_date() {
        return out_date;
    }

    public void setOut_date(Date out_date) {
        this.out_date = out_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Date getReturned_date() {
        return returned_date;
    }

    public void setReturned_date(Date returned_date) {
        this.returned_date = returned_date;
    }
}
