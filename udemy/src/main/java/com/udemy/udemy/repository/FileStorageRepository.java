package com.udemy.udemy.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileStorageRepository {

    @Value("${FOLDER}")
    private String Folder;

    public void save(String Filename, InputStream inputStream){
        try {
            Path filePath =  Path.of(Folder).resolve(Filename).normalize();
            Files.copy(inputStream,filePath);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Resource findByName(String fileName){
        try {
            Path filePath =  Path.of(Folder).resolve(fileName).normalize();
            return new UrlResource(filePath.toUri());
        }catch (IOException e){
            e.printStackTrace();
        }

        return  null;
    }
}
