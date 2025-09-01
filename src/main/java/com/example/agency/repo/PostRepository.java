package com.example.agency.repo;

import com.example.agency.model.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public PostRepository() {
        posts.add(new Post("spring-boot-seo",
                "SEO съвети за Spring Boot сайтове",
                LocalDate.now().minusDays(12),
                "Как да постигнете високи Core Web Vitals и добра индексация със Spring Boot?",
                "В тази публикация разглеждаме кеширане, компресия, sitemap и структурирани данни."));
        posts.add(new Post("ecommerce-with-java",
                "Онлайн магазин със Spring Boot: ключови практики",
                LocalDate.now().minusDays(30),
                "Проектиране на каталог, чек-аут, плащания и интеграции за стабилен eCommerce.",
                "Архитектура на модули, надеждност и наблюдение."));
    }

    public List<Post> findAll() { return posts; }
    public Optional<Post> findBySlug(String slug) {
        return posts.stream().filter(p -> p.getSlug().equals(slug)).findFirst();
    }
}
