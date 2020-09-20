package com.example.ruokaohjelma.ruokaohjelma.rest;

import com.example.ruokaohjelma.ruokaohjelma.model.PaivanAteria;
import com.example.ruokaohjelma.ruokaohjelma.repository.NimekeRepository;
import com.example.ruokaohjelma.ruokaohjelma.repository.PaivanAteriaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaivanAteriaController {

    PaivanAteriaRepository r;
    NimekeRepository nimekeRepository;

    PaivanAteriaController(PaivanAteriaRepository paivanAteriaRepository, NimekeRepository nimekeRepository){
        this.r = paivanAteriaRepository;
        this.nimekeRepository = nimekeRepository;

    }

    @GetMapping("/PaivanAteriat")
    List<PaivanAteria> getAll(){
        List<PaivanAteria> paivanAterias = r.findAll();
        paivanAterias.forEach(pa -> {
            pa.setNimekkenNimi(nimekeRepository.findById(pa.getNimeke()).get().getNimi());
        });
        return paivanAterias;
    }

}
