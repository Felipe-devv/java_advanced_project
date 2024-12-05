package com.java.project.kebab;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AddUpdateKebabRequest {
    private String name;
    private String size;
    private String meat;
    private String sauce;
    private Double price;
}
