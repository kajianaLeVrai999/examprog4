package com.example.demo.controller;

import com.example.demo.dto.FileResponse;
import com.example.demo.service.FileService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileController {

  private final FileService service;

  public FileController(FileService service) {
    this.service = service;
  }

  @GetMapping
  public List<FileResponse> findAll() {
    return service.findAll();
  }
}
