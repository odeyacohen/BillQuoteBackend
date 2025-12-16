package com.billquote.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "societe")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Societe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_soc")
  private Long id;

  @Column(name = "nom_soc", nullable = false, length = 150)
  private String nomSoc;

  @Column(name = "mail_soc", length = 255)
  private String mailSoc;

  @Column(name = "tel_soc", length = 30)
  private String telSoc;

  @Column(name = "mdp_soc", length = 255)
  private String mdpSoc;
}