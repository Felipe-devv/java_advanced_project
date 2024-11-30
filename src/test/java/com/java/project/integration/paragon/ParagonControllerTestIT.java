package com.java.project.integration.paragon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.project.paragon.ParagonPOJO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
//@WithMockUser(username = "habibi@exmaple.com",roles = "ROLE_szef")
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ParagonControllerTestIT {

    private static final String JWT="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYWJpYmlAZXhhbXBsZS5jb20iLCJpYXQiOjE3MzI5OTE4NjMsImV4cCI6MTczNTU4Mzg2M30.xN0x6UebLmSUAlrXvq-ShdMjAhyE8R3DulxmhryAPL4";


    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllParagones() throws Exception {
        mockMvc.perform(get("/paragones/all")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization",JWT))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN));
    }

    @Test
    void getParagonById() throws Exception {
        int testParagonId = 1; // Change to a valid ID if necessary

        mockMvc.perform(get("/" + testParagonId))
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

        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newParagon)))
                .andExpect(status().isOk())
                .andExpect(content().string("Paragon dodany"));
    }

    @Test
    void updateParagon() throws Exception {
        int testParagonId = 1; // Change to a valid ID if necessary
        ParagonPOJO updatedParagon = new ParagonPOJO();
        updatedParagon.setKebab(1);
        updatedParagon.setTurek(1);
        updatedParagon.setMiasto("Lublin");
        updatedParagon.setKodPocztowy("20-500");

        mockMvc.perform(put("/" + testParagonId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedParagon)))
                .andExpect(status().isOk())
                .andExpect(content().string("Paragon zaaktualizowany"));
    }

    @Test
    void deleteParagon() throws Exception {
        int testParagonId = 1;

        mockMvc.perform(delete("/" + testParagonId))
                .andExpect(status().isOk())
                .andExpect(content().string("Paragon usunięty"));
    }
}