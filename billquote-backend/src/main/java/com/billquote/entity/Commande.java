package com.billquote.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Commande {
    @Id
    @GeneratedValue
    private Long id_commande;
    private String description_com;
    private int quantite_com;
    private double prix_ttc;
    private double prix_tva;
    private double prix_unitaire;
}
