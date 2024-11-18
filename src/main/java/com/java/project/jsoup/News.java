package com.java.project.jsoup;
public class News {

    private String date;
    private String title;

    public News(String date, String title) {
        this.date = date;
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return date + "\n" + title + "\n";
    }
}
