package com.java.project.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class JSoupFeed {
    private static final String[] KEYWORDS = {"rektorski", "rektorskie", "dziekański", "dziekańskie", "wolne od zajęć"};
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public List<News> fetchNews() throws IOException {
        List<News> newsList = new ArrayList<>();
        int pageNumber = 1;

        while (true) {
            String url = "https://weii.pollub.pl/aktualnosci/page" + pageNumber + ".html";

            Document doc = Jsoup.connect(url).get();
            Elements newsElements = doc.select("div.news-item");

            if (newsElements.isEmpty()) {
                break;
            }

            for (Element newsElement : newsElements) {
                String date = newsElement.select("div.text-primary").text();
                String title = newsElement.select("a.title").text();

                if (containsKeyword(title)) {
                    newsList.add(new News(date, title));
                }
            }

            Elements nextButton = doc.select("li.page-item.next a.page-link");
            if (nextButton.isEmpty()) {
                break;
            }

            pageNumber++;
        }

        newsList.sort(Comparator.comparing(news -> {
            try {
                return DATE_FORMAT.parse(news.getDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }));

        return newsList;
    }

    private boolean containsKeyword(String title) {
        for (String keyword : KEYWORDS) {
            if (title.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}

