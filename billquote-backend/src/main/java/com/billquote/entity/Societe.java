package com.billquote.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Societe {
    @Id
    @GeneratedValue
    private Long id_soc;
    private String nom_soc;
    private String tel_soc;
    private String mail_soc;
    private String mdp_soc;
}
