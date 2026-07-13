package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.demo.dto.FileResponse;
import com.example.demo.entity.FileEntity;
import com.example.demo.repository.FileRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FileServiceTest {

  @Mock private FileRepository repository;

  @InjectMocks private FileService fileService;

  @Test
  @DisplayName("Should return a list of FileResponse when files exist in repository")
  void findAll_shouldReturnFileResponseList() {

    LocalDateTime now = LocalDateTime.now();

    FileEntity file1 = new FileEntity();
    file1.setId(1L);
    file1.setFilename("document.pdf");
    file1.setEmail("user@example.com");
    file1.setCreatedAt(now);

    FileEntity file2 = new FileEntity();
    file2.setId(2L);
    file2.setFilename("image.png");
    file2.setEmail("admin@example.com");
    file2.setCreatedAt(now);

    when(repository.findAll()).thenReturn(List.of(file1, file2));

    List<FileResponse> result = fileService.findAll();

    assertThat(result).isNotNull().hasSize(2);

    FileResponse response1 = result.get(0);
    assertThat(response1.getId()).isEqualTo(1L);
    assertThat(response1.getFilename()).isEqualTo("document.pdf");
    assertThat(response1.getEmail()).isEqualTo("user@example.com");
    assertThat(response1.getCreatedAt()).isEqualTo(now);
  }

  @Test
  @DisplayName("Should return an empty list when no files are found")
  void findAll_shouldReturnEmptyList_whenNoFilesExist() {

    when(repository.findAll()).thenReturn(Collections.emptyList());

    List<FileResponse> result = fileService.findAll();

    assertThat(result).isEmpty();
  }
}
