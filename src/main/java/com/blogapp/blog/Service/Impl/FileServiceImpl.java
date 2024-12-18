package com.blogapp.blog.Service.Impl;

import com.blogapp.blog.Service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public String uploadFile(String path,MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String randomUID = UUID.randomUUID().toString();
        String fileName1 = randomUID.concat(fileName.substring(fileName.lastIndexOf(".")));

        String filePath = path+ File.separator + fileName1;

        File f = new File(path);

        if(!f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String filePath = path+ File.separator + fileName;
        InputStream fileInputStream = new FileInputStream(filePath);
        return fileInputStream;
    }
}
