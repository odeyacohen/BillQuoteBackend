package com.billquote.model;

import jakarta.persistence.*;

@Entity
public class PdfFile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String fileName;

  private String type; // "DEVIS", "FACTURE", etc.

  @Lob
  @Column(name = "data", columnDefinition = "LONGBLOB")
  private byte[] data;

  public PdfFile() {}

  public PdfFile(String fileName, String type, byte[] data) {
    this.fileName = fileName;
    this.type = type;
    this.data = data;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

public byte[] getData() {
	return data;
}

public void setData(byte[] data) {
	this.data = data;
}

}
