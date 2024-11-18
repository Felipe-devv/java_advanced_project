package com.java.project.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class JSoupEmployee {

    public Map<String, List<String>> fetchEmployees() throws IOException {
        String url = "https://cs.pollub.pl/staff/";
        Map<String, List<String>> departmentEmployees = new HashMap<>();

        Document doc = Jsoup.connect(url).get();
        Elements departments = doc.select("article h3"); 

        for (Element department : departments) {
            String departmentName = department.text();
            Element nextElement = department.nextElementSibling();

            if (nextElement != null && nextElement.tagName().equals("p")) {
                List<String> employeeNames = new ArrayList<>();
                Elements employees = nextElement.select("a");
                for (Element employee : employees) {
                    employeeNames.add(employee.text());
                }
                Collections.sort(employeeNames);
                departmentEmployees.put(departmentName, employeeNames);
            }
        }

        return departmentEmployees;
    }
}