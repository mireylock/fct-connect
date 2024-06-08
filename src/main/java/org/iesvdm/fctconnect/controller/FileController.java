package org.iesvdm.fctconnect.controller;

import org.iesvdm.fctconnect.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/files")
public class FileController {
    private final Path root = Paths.get("uploads");

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            Path filePath = root.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), filePath);

            // Guardar path en la base de datos
            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/files/")
                    .path(file.getOriginalFilename())
                    .toUriString();
            fileService.saveFilePath(fileUri);

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + fileUri);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file: " + e.getMessage());
        }
    }

//    @GetMapping("/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//        try {
//            Path file = root.resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                        .body(resource);
//            } else {
//                throw new RuntimeException("Could not read the file!");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Error: " + e.getMessage());
//        }
//    }
}
