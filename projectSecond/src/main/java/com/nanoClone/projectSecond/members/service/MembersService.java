package com.nanoClone.projectSecond.members.service;

import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nanoClone.projectSecond.members.dao.MembersDAO;
import com.nanoClone.projectSecond.members.domain.Members;

@Service
public class MembersService {
  @Autowired
  MembersDAO membersDAO;

  public Members get(int id) {
    return membersDAO.get(id);
  }

  public void add(Members members) {
    members.setPassword(cryptoPassword(members.getPassword()));
    membersDAO.add(members);
  }

  public Members login(Members members) {
    try {
      Members tempUser = membersDAO.get(members.getUserId());
      if (tempUser != null
          && tempUser.getPassword().equals(cryptoPassword(members.getPassword()))) {
        return tempUser;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private String cryptoPassword(String password) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      md.update(password.getBytes());
      byte[] sha256Hash = md.digest();
      StringBuilder sb = new StringBuilder();
      for (byte b : sha256Hash) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}