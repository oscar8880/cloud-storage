package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

  @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
  List<Note> getUsersNotes(Integer userid);

  @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
  Note getNoteById(Integer noteid);

  @Insert("INSERT INTO NOTES (noteid, userid, notetitle, notedescription) VALUES(#{noteid}, #{userid}, #{notetitle}, #{notedescription})")
  @Options(useGeneratedKeys = true, keyProperty = "noteid")
  int addNote(Note note);

  @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
  int editNote(Integer noteid, String notetitle, String notedescription);

  @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
  int deleteNote(Integer noteid);
}
