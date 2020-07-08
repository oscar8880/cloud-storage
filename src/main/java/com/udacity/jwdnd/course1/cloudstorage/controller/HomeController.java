package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

  private CredentialService credentialService;
  private FileService fileService;
  private NoteService noteService;
  private UserService userService;

  public HomeController(CredentialService credentialService, NoteService noteService, UserService userService, FileService fileService) {
    this.credentialService = credentialService;
    this.fileService = fileService;
    this.noteService = noteService;
    this.userService = userService;
  }

  @GetMapping()
  public String homeView(@ModelAttribute("noteForm") NoteForm noteForm, @ModelAttribute("credentialForm") CredentialForm credentialForm, Authentication authentication, Model model) {
    User currentUser = this.userService.getUser(authentication.getName());
    model.addAttribute("usersNotes", this.noteService.getUsersNotes(currentUser.getUserId()));
    model.addAttribute("usersFiles", this.fileService.getUsersFiles(currentUser.getUserId()));
    model.addAttribute("usersCredentials", this.credentialService.getUsersCredentials(currentUser.getUserId()));
    model.addAttribute("usersPasswords", this.credentialService.getUnencryptedPasswords(currentUser.getUserId()));
    return "home";
  }
}


