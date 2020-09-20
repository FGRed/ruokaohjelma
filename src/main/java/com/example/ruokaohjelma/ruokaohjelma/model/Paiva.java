package com.example.ruokaohjelma.ruokaohjelma.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Paiva {
    @Id
    @GeneratedValue
    private Long id;
    private Date pvm;
    boolean syoty;
    @Transient
    private String pvmNimi;

    @ElementCollection
    private List<Long> paivanAteriaIds;

    @Transient
    private List<PaivanAteria> PaivanAteriat;

}
