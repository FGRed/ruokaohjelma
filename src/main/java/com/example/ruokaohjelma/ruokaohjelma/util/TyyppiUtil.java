package com.example.ruokaohjelma.ruokaohjelma.util;

import com.example.ruokaohjelma.ruokaohjelma.enums.Tyyppi;

public class TyyppiUtil {

    public static Tyyppi getTyyppitByString(String tyyppi){
        if (tyyppi.equals(Tyyppi.AAMIAINEN.toString())) {
            return Tyyppi.AAMIAINEN;
        } else if (tyyppi.equals(Tyyppi.PAIVALLINEN.toString())){
            return Tyyppi.PAIVALLINEN;
        } else if(tyyppi.equals(Tyyppi.LOUNAS.toString())){
            return Tyyppi.LOUNAS;
        } else if(tyyppi.equals(Tyyppi.ILLALLINEN.toString())){
            return Tyyppi.ILLALLINEN;
        } else {
            return Tyyppi.RUOKAAINE;
        }
    }

}
