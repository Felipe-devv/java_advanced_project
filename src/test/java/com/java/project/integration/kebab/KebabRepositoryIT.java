package com.java.project.integration.kebab;

import com.java.project.kebab.KebabRepository;
import com.java.project.kebab.domain.Kebab;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ActiveProfiles("test")
public class KebabRepositoryIT {
    @Autowired
    private KebabRepository kebabRepository;
    
    @Test
    void testFindByIsActiveTrue() {
        // Given
        Kebab kebab1 = Kebab.builder()
                .name("Kebab 1")
                .size("Large")
                .meat("Chicken")
                .sauce("Garlic")
                .price(10.0)
                .isActive(true)
                .build();
        Kebab kebab2 = Kebab.builder()
                .name("Kebab 2")
                .size("Medium")
                .meat("Beef")
                .sauce("Spicy")
                .price(8.0)
                .isActive(false)
                .build();
        kebabRepository.save(kebab1);
        kebabRepository.save(kebab2);
                // When
                List<Kebab> activeKebabs = kebabRepository.findByIsActiveTrue();
                // Then
                assertThat(activeKebabs).hasSize(1);
                assertThat(activeKebabs.get(0).getName()).isEqualTo("Kebab 1");
            }
        }
