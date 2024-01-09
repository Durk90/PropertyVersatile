package com.project.PropertyVersatile.json;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class JsonFileLoader {

    public void loadJsonFile() {
        try {
            // Load the JSON file from the resources directory
            Resource resource = new ClassPathResource("Ireland.json");
            Path jsonFilePath = Files.createTempFile("tempfile", ".json");
            Files.copy(resource.getInputStream(), jsonFilePath, StandardCopyOption.REPLACE_EXISTING);

        
        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}