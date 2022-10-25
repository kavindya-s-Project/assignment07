package com.example.assignment07.models;

public class AuthorModel {
    private String book_id;
    private String author_name;

    public AuthorModel() {
    }

    public AuthorModel(String book_id, String author_name) {
        this.book_id = book_id;
        this.author_name = author_name;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
