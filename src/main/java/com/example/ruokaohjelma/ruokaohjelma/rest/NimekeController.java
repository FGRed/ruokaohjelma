package com.example.ruokaohjelma.ruokaohjelma.rest;

import com.example.ruokaohjelma.ruokaohjelma.enums.Tyyppi;
import com.example.ruokaohjelma.ruokaohjelma.exception.NimekeNotFoundException;
import com.example.ruokaohjelma.ruokaohjelma.model.Nimeke;
import com.example.ruokaohjelma.ruokaohjelma.model.ReseptinAinesosa;
import com.example.ruokaohjelma.ruokaohjelma.repository.NimekeRepository;
import com.example.ruokaohjelma.ruokaohjelma.repository.ReseptiAinesosaRepository;
import com.example.ruokaohjelma.ruokaohjelma.util.TyyppiUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class NimekeController {

    private NimekeRepository r;
    private ReseptiAinesosaRepository reseptiAinesosaRepository;

    NimekeController(NimekeRepository nimekeRepository, ReseptiAinesosaRepository reseptiAinesosaRepository){
        this.r = nimekeRepository;
        this.reseptiAinesosaRepository = reseptiAinesosaRepository;
    }

    @GetMapping("/nimekkeet")
    List<Nimeke> all(){
       return r.findAll();
    }

    @PostMapping(value = "/nimekkeet/get-by-tyyppi", produces = "application/json")
    List<Nimeke> getByTyyppi(HttpServletRequest request){
        Tyyppi t = TyyppiUtil.getTyyppitByString(request.getParameter("selected-tyyppi"));
        List<Nimeke> list = r.getAllByTyypi(t);
        return r.getAllByTyypi(t);
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

        String[] ruokaAineet = request.getParameter("ruoka-aineet").split(",");

        

        List<String> ruokaAineNimet = r.findAllById(CollectionUtils.arrayToList(ruokaAineet));
        for (String r : ruokaAineet){
            ReseptinAinesosa reseptinAinesosa = new ReseptinAinesosa();
            String res
            reseptinAinesosa.setNimi(r);
        }

        Nimeke n = new Nimeke();
        n.setNimi(nimi);
        n.setTyyppi(TyyppiUtil.getTyyppitByString(tyyppi));
        r.save(n);
        if(n.getTyyppi().equals(Tyyppi.RUOKAAINE)){
            return r.getRuokaAine();
        }else {
            return r.allNoRaakaaine();
        }
    }






}
