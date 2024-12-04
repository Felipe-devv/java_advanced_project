package com.java.project.kebab;

import jakarta.persistence.*;
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
@Entity
@Table(name = "Kebab")
public class Kebab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nazwa", length = 45, nullable = false)
    private String name;

    @Column(name = "rozmiar", length = 45, nullable = false)
    private String size;

    @Column(name = "mieso", length = 45, nullable = false)
    private String meat;

    @Column(name = "sos", length = 45, nullable = false)
    private String sauce;

    @Column(name = "cena", nullable = false)
    private Double price;

    @Column(name = "czyAktywne", nullable = false)
    private Boolean isActive;

    public boolean isValid() {
        return id != null && name != null && !name.isEmpty() && size != null && !size.isEmpty() && meat != null && !meat.isEmpty() && sauce != null && !sauce.isEmpty() &&  price >0;
    }
}
