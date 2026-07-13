package com.example.demo.service;

import com.example.demo.dto.FileResponse;
import com.example.demo.entity.FileEntity;
import com.example.demo.repository.FileRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FileService {

  private final FileRepository repository;

  public FileService(FileRepository repository) {
    this.repository = repository;
  }

  public List<FileResponse> findAll() {
    return repository.findAll().stream().map(this::toResponse).toList();
  }

  private FileResponse toResponse(FileEntity entity) {
    return new FileResponse(
        entity.getId(), entity.getFilename(), entity.getEmail(), entity.getCreatedAt());
  }
}
