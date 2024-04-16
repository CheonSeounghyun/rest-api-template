package com.wini.restapitemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestApiTemplateApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCRUDOperations() throws Exception {
        
        String uuidQuery = "SELECT api_key FROM api_key ORDER BY reg_date DESC LIMIT 1";
        String apiKey = jdbcTemplate.queryForObject(uuidQuery, String.class);
        
        // Create
        String createUserJson = "{\"user_id\": \"admin\", \"user_pwd\": \"admin\", \"user_nm\": \"testuser\", \"user_tel\": \"01011112222\"}";
        mockMvc.perform(post("/api/user")
                .header("Authentication", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserJson))
                .andExpect(status().isOk());

        // Read
        mockMvc.perform(get("/api/user/admin")
                .header("Authentication", apiKey))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user_nm").value("testuser"));

        // Update
        String updateUserJson = "{\"user_id\": \"admin\", \"user_nm\": \"updateuser\"}";
        mockMvc.perform(put("/api/user")
                .header("Authentication", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateUserJson))
                .andExpect(status().isOk());

        // Read updated user
        mockMvc.perform(get("/api/user/admin")
                .header("Authentication", apiKey))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.user_nm").value("updateuser"));

        // Delete
        mockMvc.perform(delete("/api/user/admin")
                .header("Authentication", apiKey))
                .andExpect(status().isOk());

        // Verify user deletion
        mockMvc.perform(get("/api/user/admin")
                .header("Authentication", apiKey))
                .andExpect(jsonPath("$.data.use_yn").value("N"));
    }
}
