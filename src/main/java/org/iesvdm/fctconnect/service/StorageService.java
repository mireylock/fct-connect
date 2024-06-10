package org.iesvdm.fctconnect.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class StorageService {
    @Value("${media.location}")
    private String mediaLocation;
    private Path rootLocation;

    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    public String store (MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw  new RuntimeException("Failed to store empty file");
            }
            String fileName = file.getOriginalFilename();
            assert fileName != null;
            Path destinationFile = rootLocation.resolve(Paths.get(fileName)
                    .normalize());

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return fileName;
        } catch(IOException e) {
            throw new RuntimeException("Failed to store file", e);

        }
    }

    public Resource loadAsResource(String fileName) {
        try {
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            log.info("La resource es: "+resource);

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw  new RuntimeException("Could not read file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file (malformed): " + fileName, e);
        }
    }


}
