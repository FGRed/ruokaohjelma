package com.example.ruokaohjelma.ruokaohjelma.model;

import com.example.ruokaohjelma.ruokaohjelma.enums.Tyyppi;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Entity
@EqualsAndHashCode
@ToString
public class Nimeke {
    @Id @GeneratedValue private Long id;

    private String nimi;
    private String reseptiId;
    private String kuva;
    private Tyyppi tyyppi;

    public Nimeke(String nimi){
        this.nimi = nimi;
    }

    public Nimeke(){

    }

    @Transient
    private Resepti resepti;

    public void setNimi(String nimi){
        this.nimi = nimi;
    }
}
