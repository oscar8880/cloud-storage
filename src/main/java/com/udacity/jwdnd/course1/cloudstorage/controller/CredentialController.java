package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/credential")
public class CredentialController {

  private CredentialService credentialService;
  private UserService userService;

  public CredentialController(CredentialService credentialService, UserService userService) {
    this.credentialService = credentialService;
    this.userService = userService;
  }

  @PostMapping
  public String addCredential(@ModelAttribute("credentialForm") CredentialForm credentialForm, Authentication authentication, Model model, RedirectAttributes attributes) {
    if(this.credentialService.getCredentialById(credentialForm.getCredentialid()) == null) {
      User currentUser = this.userService.getUser(authentication.getName());
      Credential newCredential = new Credential(null, credentialForm.getUrl(), credentialForm.getUsername(), null, credentialForm.getPassword(), currentUser.getUserId());
      Integer returnedCredentialId = credentialService.addCredential(newCredential);
      if (returnedCredentialId == null) {
        attributes.addAttribute("error", "Failed to add credential.");
      }
    } else {
      Integer rowsModified = credentialService.editCredential(credentialForm.getCredentialid(), credentialForm.getUrl(), credentialForm.getUsername(), credentialForm.getPassword());
      if(rowsModified == 0) {
        attributes.addAttribute("error", "Failed to update credential.");
      }
    }

    credentialForm.setUrl("");
    credentialForm.setUsername("");
    credentialForm.setPassword("");

    return "redirect:result";
  }

  @PostMapping("/delete/{credentialid}")
  public String deleteCredential(Model model, @PathVariable(value="credentialid") Integer credentialid, RedirectAttributes attributes) {
    Integer rowsModified = credentialService.deleteCredential(credentialid);
    if(rowsModified == 0) {
      attributes.addAttribute("error", "Failed to delete credential.");
    }
    return "redirect:/result";
  }
}
