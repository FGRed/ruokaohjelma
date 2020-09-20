package com.example.ruokaohjelma.ruokaohjelma.exception;

public class NimekeNotFoundException extends RuntimeException {
     public NimekeNotFoundException(Long id){
        super("ID:llä "+id+" ei löytynyt yhtään vastaavuuksia!");
    }

}
