package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

  private final FileMapper fileMapper;

  public FileService(FileMapper fileMapper) {
    this.fileMapper = fileMapper;
  }

  public List<File> getUsersFiles(Integer userid) {
    return fileMapper.getUsersFiles(userid);
  }

  public File getFileById(Integer fileid) {
    return fileMapper.getFileById(fileid);
  }

  public int addFile(File file) {
    return fileMapper.addFile(file);
  }

  public int deleteFile(Integer fileid) {
    return fileMapper.deleteFile(fileid);
  }

  public boolean fileNameAlreadyExists(String fileName, Integer userid) {
    List<File> usersFiles = fileMapper.getUsersFiles(userid);
    List<String> usersFileNames = new ArrayList<>();
    usersFiles.forEach(file -> usersFileNames.add(file.getFilename()));
    if(usersFileNames.contains(fileName)) {
      return true;
    } else {
      return false;
    }
  }
}
