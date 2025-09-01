package com.example.agency.controller;

import com.example.agency.repo.PostRepository;
import com.example.agency.model.Post;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.format.DateTimeFormatter;

@Controller
public class SitemapController {
    private final PostRepository repo;

    public SitemapController(PostRepository repo) {
        this.repo = repo;
    }


    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String sitemap() {
        String base = System.getProperty("site.baseUrl", "http://localhost:8080");
        DateTimeFormatter f = DateTimeFormatter.ISO_DATE;
        StringBuilder sb = new StringBuilder();

        sb.append("""
                <?xml version="1.0" encoding="UTF-8"?>
                <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
                """);

        String[] pages = {"/", "/services", "/portfolio", "/contact", "/blog"};
        for (String p : pages) {
            sb.append("<url><loc>").append(base).append(p).append("</loc></url>\n");
        }

        for (Post p : repo.findAll()) {
            sb.append("<url><loc>").append(base).append("/blog/").append(p.getSlug()).append("</loc>")
                    .append("<lastmod>").append(p.getDate().format(f)).append("</lastmod></url>\n");
        }

        sb.append("</urlset>");
        return sb.toString();
    }
}
