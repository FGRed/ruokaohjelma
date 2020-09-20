package com.example.ruokaohjelma.ruokaohjelma.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Resepti {
    @Id
    @GeneratedValue
    private Long id;
    private String nimi;
    private String ohje;
    @ElementCollection
    private List<Long> ainesosat;

    public Resepti(){}
}
