package com.java.project.kebab.payload;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddUpdateKebabRequest {
    private String name;
    private String size;
    private String meat;
    private String sauce;
    private double price;
}
