package com.aditya.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileUploadService {

    public void upload(MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), Paths.get(Objects.requireNonNull(file.getOriginalFilename())));
    }

    public File download(String filename) {
        return new File(filename);
    }
}
