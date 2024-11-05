package com.java.project.kebab;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Kebab {
    private Integer id;
    private String name;
    private String size;
    private String meat;
    private String sauce;
    private double price;

    public boolean isValid() {
        return id != null && name != null && !name.isEmpty() && size != null && !size.isEmpty() && meat != null && !meat.isEmpty() && sauce != null && !sauce.isEmpty() &&  price >0;
    }
}
