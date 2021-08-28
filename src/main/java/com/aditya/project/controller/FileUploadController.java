package com.aditya.project.controller;

import com.aditya.project.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService service;

    @PostMapping("")
    public void upload(@RequestParam MultipartFile file) throws IOException {
        service.upload(file);
    }

    @GetMapping("")
    public void download(@RequestParam String filename, HttpServletResponse response) throws IOException {
        File file = service.download(filename);
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
