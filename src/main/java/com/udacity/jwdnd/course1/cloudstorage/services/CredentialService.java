package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

  private final CredentialMapper credentialMapper;
  private final EncryptionService encryptionService;
  private final HashService hashService;

  public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService, HashService hashService) {
    this.credentialMapper = credentialMapper;
    this.encryptionService = encryptionService;
    this.hashService = hashService;
  }

  public List<Credential> getUsersCredentials(Integer userid) {
    return credentialMapper.getUsersCredentials(userid);
  }

  public Credential getCredentialById(Integer credentialid) {
    return credentialMapper.getCredentialById(credentialid);
  }

  public List<String> getUnencryptedPasswords(Integer userid) {
    List<Credential> credentials = credentialMapper.getUsersCredentials(userid);
    List<String> passwords = new ArrayList<>();
    credentials.forEach(credential -> {
      String unencryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
      passwords.add(unencryptedPassword);
    });

    return passwords;
  }

  public int addCredential(Credential credential) {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedSalt);

    credential.setPassword(encryptedPassword);
    credential.setKey(encodedSalt);

    return credentialMapper.addCredential(credential);
  }

  public int editCredential(Integer credentalid, String url, String username, String password) {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    String encryptedPassword = encryptionService.encryptValue(password, encodedSalt);
    
    return credentialMapper.editCredential(credentalid, url, username, encryptedPassword, encodedSalt);
  }

  public int deleteCredential(Integer credentialid) {
    return credentialMapper.deleteCredential(credentialid);
  }
}
