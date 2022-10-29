package com.example.assignment07.models;

public class BookModel {
    private int id;
    private String title;
    private String publisher;

    public BookModel() {
    }

    public BookModel(String title, int id, String publisher) {
        this.title = title;
        this.id = id;
        this.publisher = publisher;
    }

    public BookModel(String title, String publisher) {
        this.title = title;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
