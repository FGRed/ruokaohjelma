package com.example.ruokaohjelma.ruokaohjelma.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Data
public class PaivanAteria {
    @Id
    @GeneratedValue
    private long id;
    private long nimeke;
    private Date ajanKohta;
    private String kellonaika;
    @Transient
    private String nimekkenNimi;

}
