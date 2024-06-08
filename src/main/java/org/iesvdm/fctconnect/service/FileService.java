package org.iesvdm.fctconnect.service;

import org.iesvdm.fctconnect.domain.File;
import org.iesvdm.fctconnect.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public void saveFilePath(String filePath) {
        // Lógica para guardar la ruta del archivo en la base de datos
        File file = new File();
        file.setPath(filePath);
        fileRepository.save(file);
    }

}
