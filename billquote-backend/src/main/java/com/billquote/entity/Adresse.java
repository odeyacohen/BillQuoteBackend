package com.billquote.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Adresse {
    @Id
    @GeneratedValue
    private Long id_adresse;
    private String rue;
    private String code_postale;
    private String ville;
}
