package com.java.project.integration.turek;
import com.java.project.turek.Turek;
import com.java.project.turek.TurekRepository;
import com.java.project.turek.TurekService;
import com.java.project.user.User;
import com.java.project.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
//@WithMockUser(username = "habibi@exmaple.com",roles = "ROLE_szef")
@EnableAutoConfiguration(exclude= SecurityAutoConfiguration.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TurekServiceIT {

    private static final String JWT="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MTIzQHRlc3QuY29tIiwiaWF0IjoxNzMyOTg5NTU3LCJleHAiOjE3MzU1ODE1NTd9.hp2VXoGYY7JWlBf3Maj2YBCQv-_-TQSfrbJ0Nip9WwM";

    @Autowired
    UserRepository userRepository;

    @Autowired
    TurekRepository turekRepository;

    @Autowired
    TurekService turekServiceImpl;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        userRepository.save(new User("test123@test.com", "test123", "ROLE_szef"));
        turekRepository.save(new Turek(1, "Jan", "Kowalski", "Developer", 25.0, 1) );
    }

    @Test
    void testGetAllTureks() throws Exception {
        // wykonanie żądania GET na endpoint /tureks
        mockMvc.perform(get("/turek/all")
                .header(HttpHeaders.AUTHORIZATION, JWT)) // Dodajemy nagłówek z JWT.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Oczekujemy statusu 200 OK
                .andExpect(jsonPath("$[0].id").value(1)) // Oczekujemy, że pierwszy turek ma id = 1
                .andExpect(jsonPath("$[0].imie").value("Jan")) // Oczekujemy, że imię pierwszego tureka to "Jan"
                .andExpect(jsonPath("$[0].nazwisko").value("Kowalski")) // Oczekujemy, że nazwisko to "Kowalski"
                .andExpect(jsonPath("$[0].stanowisko").value("Developer")) // Oczekujemy, że stanowisko to "Developer"
                .andExpect(jsonPath("$[0].stawkaGodzinowa").value(25.0)) // Oczekujemy, że stawka godzinowa to 25.0
                .andExpect(jsonPath("$[0].status").value(1)); // Oczekujemy, że status tureka to 1
    }
    }



