package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
  List<Credential> getUsersCredentials(Integer userid);

  @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
  Credential getCredentialById(Integer credentialid);

  @Insert("INSERT INTO CREDENTIALS (credentialid, url, username, key, password, userid) VALUES(#{credentialid}, #{url}, #{username}, #{key}, #{password}, #{userid})")
  @Options(useGeneratedKeys = true, keyProperty = "credentialid")
  int addCredential(Credential credential);

  @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password}, key = #{key} WHERE credentialid = #{credentialid}")
  int editCredential(Integer credentialid, String url, String username, String password, String key);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
  int deleteCredential(Integer credentialid);
}
