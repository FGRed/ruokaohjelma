package com.example.ruokaohjelma.ruokaohjelma.model;

import com.example.ruokaohjelma.ruokaohjelma.enums.Yksikko;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ReseptinAinesosa extends Nimeke {
    int maara;
    Yksikko yksikko;
    long reseptiId;
}
