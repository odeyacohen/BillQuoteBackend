package com.billquote.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue
    private Long id_client;
    private String nom_client;
    private String prenom_client;
    private String mail_client;
    private String tel_client;
}
