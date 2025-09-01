package com.example.agency.model;

import java.time.LocalDate;

public class Post {
    private String slug;
    private String title;
    private LocalDate date;
    private String excerpt;
    private String content;

    public Post(String slug, String title, LocalDate date, String excerpt, String content) {
        this.slug = slug;
        this.title = title;
        this.date = date;
        this.excerpt = excerpt;
        this.content = content;
    }

    public String getSlug() { return slug; }
    public String getTitle() { return title; }
    public LocalDate getDate() { return date; }
    public String getExcerpt() { return excerpt; }
    public String getContent() { return content; }
}
