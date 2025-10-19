package com.example.formsys.controller;

import com.example.formsys.repository.FormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FormIntegrationTest {

    @Autowired
    FormRepository formRepository;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void cleanDb(){
        formRepository.deleteAll();
    }

    @Test
    void creatAndGetForm() throws Exception{

        String json = """
                { "title" = "IntegrationstestForm",
                "description" = "Form für Integrationstest",
                "schema_json" = "{}"
                }
                """;

        mockMvc.perform(post("/api/forms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("IntegrationTestForm"));
    }

}
