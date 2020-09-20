package com.example.ruokaohjelma.ruokaohjelma.rest;

import com.example.ruokaohjelma.ruokaohjelma.enums.Tyyppi;
import com.example.ruokaohjelma.ruokaohjelma.exception.NimekeNotFoundException;
import com.example.ruokaohjelma.ruokaohjelma.model.Nimeke;
import com.example.ruokaohjelma.ruokaohjelma.repository.NimekeRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class NimekeController {

    private NimekeRepository r;

    NimekeController(NimekeRepository nimekeRepository){
        this.r = nimekeRepository;
    }

    @GetMapping("/nimekkeet")
    List<Nimeke> all(){
       return r.findAll();
    }

    @PostMapping("/nimekkeet")
    Nimeke saveNimeke(@RequestBody Nimeke nimeke){
        return r.save(nimeke);
    }

    @GetMapping("/nimekkeet/testsave")
    Nimeke testsSave(){
       Nimeke nimeke = new Nimeke("Nimeke");
       return r.save(nimeke);
    }

    @GetMapping("/nimekkeet/{id}")
    Nimeke getNimekeById(@PathVariable Long id){
        return r.findById(id).orElseThrow(() -> new NimekeNotFoundException(id));
    }

    @PostMapping("/lisaa-nimike")
    public List<Nimeke> processSubmit(HttpServletRequest request) {
        String nimi = request.getParameter("nimi");
        String tyyppi = request.getParameter("tyyppi");
        Nimeke n = new Nimeke();
        n.setNimi(nimi);
        if (tyyppi.equals(Tyyppi.AAMIANEN.toString())) {
            n.setTyyppi(Tyyppi.AAMIANEN);
        } else if (tyyppi.equals(Tyyppi.PAIVALLINEN.toString())){
            n.setTyyppi(Tyyppi.PAIVALLINEN);
        } else if(tyyppi.equals(Tyyppi.LOUNAS.toString())){
            n.setTyyppi(Tyyppi.LOUNAS);
        } else if(tyyppi.equals(Tyyppi.ILLALLINEN.toString())){
            n.setTyyppi(Tyyppi.ILLALLINEN);
        } else{
            n.setTyyppi(Tyyppi.RUOKAAINE);
        }

        r.save(n);
        if(n.getTyyppi().equals(Tyyppi.RUOKAAINE)){
            return r.getRuokaAine();
        }else {
            return r.allNoRaakaaine();
        }
    }







}
