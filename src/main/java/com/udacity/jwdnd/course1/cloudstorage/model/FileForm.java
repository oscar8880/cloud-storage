package com.udacity.jwdnd.course1.cloudstorage.model;

public class FileForm {
  private Integer fileid;
  private String filename;
  private String contenttype;
  private String filesize;
  private byte[] filedata;

  public FileForm(Integer fileid, String filename, String contenttype, String filesize, byte[] filedata) {
    this.fileid = fileid;
    this.filename = filename;
    this.contenttype = contenttype;
    this.filesize = filesize;
    this.filedata = filedata;
  }

  public Integer getFileid() {
    return fileid;
  }

  public void setFileid(Integer fileid) {
    this.fileid = fileid;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public String getContenttype() {
    return contenttype;
  }

  public void setContenttype(String contenttype) {
    this.contenttype = contenttype;
  }

  public String getFilesize() {
    return filesize;
  }

  public void setFilesize(String filesize) {
    this.filesize = filesize;
  }

  public byte[] getFiledata() {
    return filedata;
  }

  public void setFiledata(byte[] filedata) {
    this.filedata = filedata;
  }
}
