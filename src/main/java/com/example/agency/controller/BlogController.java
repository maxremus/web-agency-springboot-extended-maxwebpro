package com.example.agency.controller;

import com.example.agency.repo.PostRepository;
import com.example.agency.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BlogController {

    private final PostRepository repo;

    public BlogController(PostRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("page", "blog");
        model.addAttribute("posts", repo.findAll());
        return "blog";
    }

    @GetMapping("/blog/{slug}")
    public String post(@PathVariable String slug, Model model) {
        Post p = repo.findBySlug(slug).orElse(null);
        if (p == null) {
            model.addAttribute("page", "blog");
            return "blog";
        }
        model.addAttribute("post", p);
        model.addAttribute("page", "blog");
        return "post";
    }
}
