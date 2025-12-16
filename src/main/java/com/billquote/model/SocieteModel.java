package com.billquote.model;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Builder
public class SocieteModel {
  private Long id;
  private String nomSoc;
  private String mailSoc;
  private String telSoc;
  private String mdpSoc;
  
  
@Override
public String toString() {
	return "SocieteModel [id=" + id + ", nomSoc=" + nomSoc + ", mailSoc=" + mailSoc + ", telSoc=" + telSoc + ", mdpSoc="
			+ mdpSoc + "]";
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getNomSoc() {
	return nomSoc;
}


public void setNomSoc(String nomSoc) {
	this.nomSoc = nomSoc;
}


public String getMailSoc() {
	return mailSoc;
}


public void setMailSoc(String mailSoc) {
	this.mailSoc = mailSoc;
}


public String getTelSoc() {
	return telSoc;
}


public void setTelSoc(String telSoc) {
	this.telSoc = telSoc;
}


public String getMdpSoc() {
	return mdpSoc;
}


public void setMdpSoc(String mdpSoc) {
	this.mdpSoc = mdpSoc;
}
  
  
}