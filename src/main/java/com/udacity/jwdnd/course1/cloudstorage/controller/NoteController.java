package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/note")
public class NoteController {

  private NoteService noteService;
  private UserService userService;

  public NoteController(NoteService noteService, UserService userService) {
    this.noteService = noteService;
    this.userService = userService;
  }

  @PostMapping
  public String addNote(@ModelAttribute("noteForm") NoteForm noteForm, Authentication authentication, Model model, RedirectAttributes attributes) {
    if(this.noteService.getNoteById(noteForm.getNoteid()) == null) {
      User currentUser = this.userService.getUser(authentication.getName());
      Note newNote = new Note(null, currentUser.getUserId(), noteForm.getNotetitle(), noteForm.getNotedescription());
      Integer returnedNoteId = noteService.addNote(newNote);
      if (returnedNoteId == null) {
        attributes.addAttribute("error", "Failed to add note.");
      }
    } else {
      Integer rowsModified = noteService.editNote(noteForm.getNoteid(), noteForm.getNotetitle(), noteForm.getNotedescription());
      if(rowsModified == 0) {
        attributes.addAttribute("error", "Failed to update note.");
      }
    }

    noteForm.setNotedescription("");
    noteForm.setNotetitle("");
    return "redirect:result";
  }

  @PostMapping("/delete/{noteid}")
  public String deleteNote(Model model, @PathVariable(value="noteid") Integer noteid, RedirectAttributes attributes) {
    Integer rowsModified = noteService.deleteNote(noteid);
    if(rowsModified == 0) {
      attributes.addAttribute("error", "Failed to delete note.");
    }
    return "redirect:/result";
  }
}
