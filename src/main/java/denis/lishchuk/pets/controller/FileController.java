package denis.lishchuk.pets.controller;

import denis.lishchuk.pets.dto.request.FileRequest;
import denis.lishchuk.pets.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String upload(@RequestBody FileRequest fileRequest) throws IOException {
        String filePath = fileService.saveFile(fileRequest);
        return filePath;
    }
}
