package com.billquote.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter

public class Utilisateur {
	@Id
    private String email;

    private String nom;
    private String prenom;
    private String motDePasse;
    private String tel;
    private String role;
    

    @Override
	public String toString() {
		return "Utilisateur [email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", motDePasse=" + motDePasse
				+ ", tel=" + tel + ", role=" + role + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
