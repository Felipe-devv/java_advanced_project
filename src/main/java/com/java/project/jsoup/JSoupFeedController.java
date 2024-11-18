package com.java.project.jsoup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/news")
public class JSoupFeedController {

    @Autowired
    private JSoupFeed jSoupFeed;

    @GetMapping
    public String getNews() {
        try {
            List<News> newsList = jSoupFeed.fetchNews();
            StringBuilder result = new StringBuilder();
            for (News news : newsList) {
                result.append(news.toString()).append("\n");
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to fetch news from the server";
        }
    }
}