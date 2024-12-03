package com.java.project.integration.kebab;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.project.kebab.KebabRepository;
import com.java.project.kebab.domain.Kebab;
import com.java.project.kebab.domain.KebabDTO;
import com.java.project.kebab.payload.AddUpdateKebabRequest;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@Transactional
public class KebabControllerIT {

    @Autowired
    private KebabRepository kebabRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /*
     * GET kebab/all
     */
    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testGetKebabs() throws Exception {
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
        MvcResult mvcResult = mockMvc.perform(get("/kebab/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Then
        List<KebabDTO> kebabDTOs = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });
        assertThat(kebabDTOs.size(), is(1));
        assertThat(kebabDTOs.get(0).getName(), is("Kebab 1"));
    }

    /*
     * GET kebab/{kebabId}
     */

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testGetKebabById() throws Exception {
        // Given
        Kebab kebab1 = Kebab.builder()
                .name("Kebab 1")
                .size("Large")
                .meat("Chicken")
                .sauce("Garlic")
                .price(10.0)
                .isActive(true)
                .build();
        kebabRepository.save(kebab1);

        // When
        MvcResult mvcResult = mockMvc.perform(get("/kebab/" + kebab1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Then
        KebabDTO kebabDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });
        assertThat(kebabDTO.getName(), is("Kebab 1"));
    }

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testGetKebabByIdNotFound() throws Exception {
        // When
        MvcResult mvcResult = mockMvc.perform(get("/kebab/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab not found"));
    }

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testGetKebabByIdNotActive() throws Exception {
        // Given
        Kebab kebab1 = Kebab.builder()
                .name("Kebab 1")
                .size("Large")
                .meat("Chicken")
                .sauce("Garlic")
                .price(10.0)
                .isActive(false)
                .build();
        kebabRepository.save(kebab1);

        // When
        MvcResult mvcResult = mockMvc.perform(get("/kebab/" + kebab1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab is not active"));
    }

    /*
     * POST kebab
     */

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testAddNewKebab() throws Exception {
        // Given
        AddUpdateKebabRequest request = AddUpdateKebabRequest.builder()
                .name("Kebab 1")
                .size("Large")
                .meat("Chicken")
                .sauce("Garlic")
                .price(10.0)
                .build();

        // When
        MvcResult mvcResult = mockMvc.perform(post("/kebab")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab added"));

        Optional<Kebab> addedKebab = kebabRepository.findAll().stream()
                .filter(kebab -> kebab.getName().equals("Kebab 1"))
                .findFirst();

        assertThat(addedKebab.isPresent(), is(true));
        assertThat(addedKebab.get().getName(), is("Kebab 1"));
        assertThat(addedKebab.get().getSize(), is("Large"));
        assertThat(addedKebab.get().getMeat(), is("Chicken"));
        assertThat(addedKebab.get().getSauce(), is("Garlic"));
        assertThat(addedKebab.get().getPrice(), is(10.0));
        assertThat(addedKebab.get().getIsActive(), is(true));
    }

    /*
     * PUT kebab/{kebabId}
     */

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testUpdateKebab() throws Exception {
        // Given
        Kebab kebab1 = Kebab.builder()
                .name("Kebab 1")
                .size("Large")
                .meat("Chicken")
                .sauce("Garlic")
                .price(10.0)
                .isActive(true)
                .build();
        kebabRepository.save(kebab1);

        AddUpdateKebabRequest request = AddUpdateKebabRequest.builder()
                .name("Kebab 2")
                .size("Medium")
                .meat("Beef")
                .sauce("Spicy")
                .price(8.0)
                .build();

        // When
        MvcResult mvcResult = mockMvc.perform(put("/kebab/" + kebab1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab updated"));
        assertThat(kebab1.getName(), is("Kebab 2"));
    }

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testUpdateKebabNotFound() throws Exception {
        // Given
        AddUpdateKebabRequest request = AddUpdateKebabRequest.builder()
                .name("Kebab 2")
                .size("Medium")
                .meat("Beef")
                .sauce("Spicy")
                .price(8.0)
                .build();

        // When
        MvcResult mvcResult = mockMvc.perform(put("/kebab/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab not found"));
    }

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testUpdateKebabNotActive() throws Exception {
        // Given
        Kebab kebab1 = Kebab.builder()
                .name("Kebab 1")
                .size("Large")
                .meat("Chicken")
                .sauce("Garlic")
                .price(10.0)
                .isActive(false)
                .build();
        kebabRepository.save(kebab1);

        AddUpdateKebabRequest request = AddUpdateKebabRequest.builder()
                .name("Kebab 2")
                .size("Medium")
                .meat("Beef")
                .sauce("Spicy")
                .price(8.0)
                .build();

        // When
        MvcResult mvcResult = mockMvc.perform(put("/kebab/" + kebab1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab is not active"));
    }

    /*
     * DELETE kebab/{kebabId}
     */

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testDeleteKebab() throws Exception {
        // Given
        Kebab kebab1 = Kebab.builder()
                .name("Kebab 1")
                .size("Large")
                .meat("Chicken")
                .sauce("Garlic")
                .price(10.0)
                .isActive(true)
                .build();
        kebabRepository.save(kebab1);

        // When
        MvcResult mvcResult = mockMvc.perform(delete("/kebab/" + kebab1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab deleted"));
    }

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testDeleteKebabNotFound() throws Exception {
        // When
        MvcResult mvcResult = mockMvc.perform(delete("/kebab/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab not found"));
    }

    /*
     * DELETE kebab/deactivate/{kebabId}
     */

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testDeactivateKebab() throws Exception {
        // Given
        Kebab kebab1 = Kebab.builder()
                .name("Kebab 1")
                .size("Large")
                .meat("Chicken")
                .sauce("Garlic")
                .price(10.0)
                .isActive(true)
                .build();
        kebabRepository.save(kebab1);

        // When
        MvcResult mvcResult = mockMvc.perform(delete("/kebab/deactivate/" + +kebab1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab deactivated"));
    }

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testDeactivateKebabNotFound() throws Exception {
        // When
        MvcResult mvcResult = mockMvc.perform(delete("/kebab/deactivate/9999")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab not found"));
    }

    @Test
    @WithMockUser(username = "dawda", roles = "regular")
    void testDeactivateKebabNotActive() throws Exception {
        // Given
        Kebab kebab1 = Kebab.builder()
                .name("Kebab 1")
                .size("Large")
                .meat("Chicken")
                .sauce("Garlic")
                .price(10.0)
                .isActive(false)
                .build();
        kebabRepository.save(kebab1);

        // When
        MvcResult mvcResult = mockMvc.perform(delete("/kebab/deactivate/" + kebab1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        // Then
        assertThat(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), is("Kebab is not active"));
    }

}
