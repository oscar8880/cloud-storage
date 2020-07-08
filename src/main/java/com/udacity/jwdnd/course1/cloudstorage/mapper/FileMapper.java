package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
  @Select("SELECT * FROM FILES WHERE userid = #{userid}")
  List<File> getUsersFiles(Integer userid);

  @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
  File getFileById(Integer fileid);

  @Insert("INSERT INTO FILES (fileid, filename, contenttype, filesize, userid, filedata) VALUES(#{fileid}, #{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
  @Options(useGeneratedKeys = true, keyProperty = "fileid")
  int addFile(File file);

  @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
  int deleteFile(Integer fileid);
}
