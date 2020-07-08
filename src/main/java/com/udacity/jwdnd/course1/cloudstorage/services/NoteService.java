package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

  private final NoteMapper noteMapper;

  public NoteService(NoteMapper noteMapper) {
    this.noteMapper = noteMapper;
  }

  public List<Note> getUsersNotes(Integer userid) {
    return noteMapper.getUsersNotes(userid);
  }

  public Note getNoteById(Integer noteid) {
    return noteMapper.getNoteById(noteid);
  }

  public int addNote(Note note) {
    return noteMapper.addNote(note);
  }

  public int editNote(Integer noteid, String notetitle, String notedescription) {
    return noteMapper.editNote(noteid, notetitle, notedescription);
  }

  public int deleteNote(Integer noteid) {
    return noteMapper.deleteNote(noteid);
  }
}
