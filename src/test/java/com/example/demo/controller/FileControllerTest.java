package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.dto.FileResponse;
import com.example.demo.service.FileService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FileController.class)
class FileControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private FileService fileService;

  @Test
  @DisplayName("GET /files should return 200 OK and a JSON array of files")
  void findAll_shouldReturnFileResponses() throws Exception {
    // Arrange
    LocalDateTime now = LocalDateTime.of(2026, 7, 13, 10, 0); // Date fixe pour éviter les surprises
    FileResponse response1 = new FileResponse(1L, "document.pdf", "user@example.com", now);
    FileResponse response2 = new FileResponse(2L, "image.png", "admin@example.com", now);

    when(fileService.findAll()).thenReturn(List.of(response1, response2));

    mockMvc
        .perform(get("/files").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].filename").value("document.pdf"))
        .andExpect(jsonPath("$[0].email").value("user@example.com"))
        .andExpect(jsonPath("$[1].id").value(2))
        .andExpect(jsonPath("$[1].filename").value("image.png"));
  }

  @Test
  @DisplayName("GET /files should return 200 OK and an empty array when no files exist")
  void findAll_shouldReturnEmptyArray_whenNoFiles() throws Exception {

    when(fileService.findAll()).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/files"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));
  }
}
