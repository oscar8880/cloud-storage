package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

  private final FileService fileService;
  private UserService userService;

  public FileController(FileService fileService, UserService userService) {
    this.fileService = fileService;
    this.userService = userService;
  }

  @GetMapping("/download/{fileid}")
  public String downloadFile(@PathVariable(value="fileid") Integer fileid, HttpServletResponse response) throws IOException {
    File file = this.fileService.getFileById(fileid);
    response.setContentType(file.getContenttype());
    response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
    response.setContentLength(Integer.parseInt(file.getFilesize()));
    FileCopyUtils.copy(file.getFiledata(), response.getOutputStream());
    return "redirect:/result";
  }

  @PostMapping("/upload")
  public String handleFileUpload(@RequestParam("fileUpload") MultipartFile fileUpload,
                                 Authentication authentication,
                                 RedirectAttributes attributes) throws IOException {

    if(fileUpload.getSize() > 2097152) {
      attributes.addAttribute("error", "File size must not exceed 2MB. ");
      return "redirect:/result";
    }

    User currentUser = this.userService.getUser(authentication.getName());
    if(fileService.fileNameAlreadyExists(fileUpload.getOriginalFilename(), currentUser.getUserId())) {
      attributes.addAttribute("error", "A file with that name already exists. ");
      return "redirect:/result";
    }

    byte[] bytes = (byte[]) fileUpload.getBytes();
    File newFile = new File(null,
        fileUpload.getOriginalFilename(),
        fileUpload.getContentType(),
        Long.toString(fileUpload.getSize()), currentUser.getUserId(), bytes);


    Integer returnedFileID = fileService.addFile(newFile);
    if(returnedFileID == null) {
      attributes.addAttribute("error", "Failed to upload file.");
    }

    return "redirect:/result";
  }

  @PostMapping("delete/{fileid}")
  public String deleteFile(Model model, @PathVariable(value="fileid") Integer fileid, RedirectAttributes attributes) {
    Integer rowsModified = fileService.deleteFile(fileid);
    if(rowsModified == 0) {
      attributes.addAttribute("error", "Failed to delete file.");
    }
    return "redirect:/result";
  }
}
