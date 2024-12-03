package com.java.project.integration.paragon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.project.paragon.Paragon;
import com.java.project.paragon.ParagonPOJO;
import com.java.project.paragon.ParagonRepository;
import com.java.project.user.User;
import com.java.project.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
//@WithMockUser(username = "habibi@exmaple.com",roles = "ROLE_regular")
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext (classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ParagonControllerTestIT {

    private static final String JWT="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYWJpYmlAZXhhbXBsZS5jb20iLCJpYXQiOjE3MzI5OTE4NjMsImV4cCI6MTczNTU4Mzg2M30.xN0x6UebLmSUAlrXvq-ShdMjAhyE8R3DulxmhryAPL4";

    @Autowired
    UserRepository userRepository;
    @Autowired
    ParagonRepository paragonRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        userRepository.save(new User("habibi@example.com", "Habibi", "ROLE_regular"));
        paragonRepository.save(new Paragon("Lubilin","19-900",1,1,50.5));
    }

    @AfterEach
    void clear()
    {
        userRepository.deleteAll();
        paragonRepository.deleteAll();
    }

    @Test
    void getAllParagones() throws Exception {

        mockMvc.perform(get("/paragones/all")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization",JWT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getParagonById() throws Exception {
        int testParagonId = 1;

        mockMvc.perform(get("/paragones/" + testParagonId).header("Authorization",JWT))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testParagonId));
    }

    @Test
    void createParagon() throws Exception {
        ParagonPOJO newParagon = new ParagonPOJO();
        newParagon.setKebab(1);
        newParagon.setTurek(1);
        newParagon.setMiasto("Lublin");
        newParagon.setKodPocztowy("20-500");

        mockMvc.perform(post("/paragones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newParagon))
                        .header("Authorization",JWT))
                .andExpect(status().isOk())
                .andExpect(content().string("Paragon dodany"));
    }

    @Test
    void updateParagon() throws Exception {
        int testParagonId = 1;
        ParagonPOJO updatedParagon = new ParagonPOJO();
        updatedParagon.setKebab(1);
        updatedParagon.setTurek(1);
        updatedParagon.setSuma(900.6);
        updatedParagon.setMiasto("Lublin");
        updatedParagon.setKodPocztowy("20-500");

        mockMvc.perform(put("/paragones/" + testParagonId)
                        .contentType(MediaType.APPLICATION_JSON).header("Authorization",JWT)
                        .content(new ObjectMapper().writeValueAsString(updatedParagon)))
                .andExpect(status().isOk())
                .andExpect(content().string("Paragon zaaktualizowany"));
    }

    @Test
    void deleteParagon() throws Exception {
        int testParagonId = 1;

        mockMvc.perform(delete("/paragones/" + testParagonId).header("Authorization",JWT))
                .andExpect(status().isOk())
                .andExpect(content().string("Paragon usunięty"));
    }
}