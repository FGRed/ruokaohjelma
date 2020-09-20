package com.example.ruokaohjelma.ruokaohjelma.rest;

import com.example.ruokaohjelma.ruokaohjelma.model.Resepti;
import com.example.ruokaohjelma.ruokaohjelma.repository.ReseptiRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReseptiController {

    ReseptiRepository r;

    ReseptiController(ReseptiRepository reseptiRepository){
        this.r = reseptiRepository;
    }
    
    @GetMapping("reseptit")
    public List<Resepti> getAll(){
       return this.r.findAll();
    }


}
