package com.billquote.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


public class SocieteDTO {
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

	private Long id; // id_soc

	  @NotBlank
	  @Size(max = 150)
	  private String nomSoc; // nom_soc

	  @Email
	  @Size(max = 255)
	  private String mailSoc; // mail_soc

	  @Size(max = 30)
	  private String telSoc; // tel_soc

	  @Size(max = 255)
	  private String mdpSoc; // mdp_soc
	  
	  
	
}
