package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
  private Integer noteid;
  private Integer userid;
  private String notetitle;
  private String notedescription;

  public Note(Integer noteid, Integer userid, String notetitle, String notedescription) {
    this.noteid = noteid;
    this.userid = userid;
    this.notetitle = notetitle;
    this.notedescription = notedescription;
  }

  public Integer getNoteid() {
    return noteid;
  }

  public void setNoteid(Integer noteid) {
    this.noteid = noteid;
  }

  public Integer getUserid() {
    return userid;
  }

  public void setUserid(Integer userid) {
    this.userid = userid;
  }

  public String getNotetitle() {
    return notetitle;
  }

  public void setNotetitle(String notetitle) {
    this.notetitle = notetitle;
  }

  public String getNotedescription() {
    return notedescription;
  }

  public void setNotedescription(String notedescription) {
    this.notedescription = notedescription;
  }
}