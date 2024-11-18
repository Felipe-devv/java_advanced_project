package com.java.project.jsoup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class JSoupController {
    @Autowired
    private JSoupEmployee jSoupEmployee;

    @GetMapping
    public String getEmployees() {
        try {
            Map<String, List<String>> departmentEmployees = jSoupEmployee.fetchEmployees();
            return buildResultString(departmentEmployees);

        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to fetch employees from the server";
        }
    }
  
    @GetMapping("/drs")
    public String getEmployeesWithDr() {
        try {
            Map<String, List<String>> departmentEmployees = jSoupEmployee.fetchEmployeesWithDr();
            return buildResultString(departmentEmployees);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to fetch employees with dr from the server";
        }
    }


    private String buildResultString(Map<String, List<String>> departmentEmployees)
    {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, List<String>> entry : departmentEmployees.entrySet()) {
            result.append(entry.getKey()).append("\n");
            for (String employee : entry.getValue()) {
                result.append(" - ").append(employee).append("\n");
            }
            result.append("\n");
        }

        return result.toString();
    }


}
