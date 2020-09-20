package com.example.ruokaohjelma.ruokaohjelma.rest;

import com.example.ruokaohjelma.ruokaohjelma.AteriaParameterBuilder;
import com.example.ruokaohjelma.ruokaohjelma.model.PaivanAteria;
import com.example.ruokaohjelma.ruokaohjelma.model.Paiva;
import com.example.ruokaohjelma.ruokaohjelma.repository.PaivanAteriaRepository;
import com.example.ruokaohjelma.ruokaohjelma.repository.NimekeRepository;
import com.example.ruokaohjelma.ruokaohjelma.repository.PaivaRepository;
import com.example.ruokaohjelma.ruokaohjelma.service.ViikkoListaRadomizer;
import com.example.ruokaohjelma.ruokaohjelma.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;



@RestController
public class PaivaController  {
    private PaivaRepository r;
    private PaivanAteriaRepository paivanAteriaRepository;
    private NimekeRepository nimekeRepository;

    @Autowired
    private ViikkoListaRadomizer viikkoListaRadomizer;

    PaivaController(PaivaRepository paivaRepository, PaivanAteriaRepository paivanAteriaRepository, NimekeRepository nimekeRepository){
        this.r = paivaRepository;
        this.paivanAteriaRepository = paivanAteriaRepository;
        this.nimekeRepository = nimekeRepository;
    }

    @GetMapping("/paivat")
    List<Paiva> getAll(){
        List<Paiva> paivas = r.findAll();
            paivas.forEach( p -> {
                p.setPvmNimi(TimeUtil.getPaivaNimi(p.getPvm()));
                p.setPaivanAteriat(deducePaivanAteriat(p.getPvm()));
            });
        if(viikkoListaRadomizer == null){
            return null;
        }
        return paivas;
    }

    @GetMapping("/paivat-all")
    List<Paiva> getAllPaivas(){
        List<Paiva> paivas = r.findAll();
        paivas.forEach(p ->{
            if(p.getPaivanAteriaIds() != null) {
                List<PaivanAteria> paivanAterias = paivanAteriaRepository.findByIds(p.getPaivanAteriaIds());
                paivanAterias.forEach(pr ->{;
                    pr.setNimekkenNimi(nimekeRepository.getNimiById(pr.getNimeke()).get(0));
                });
                p.setPaivanAteriat(paivanAterias);
                p.setPvmNimi(TimeUtil.getPaivaNimi(p.getPvm()));
            }
        });

        return paivas;
    }


    List<PaivanAteria> deducePaivanAteriat(Date date){
        Date min = TimeUtil.atStartOfDay(date);
        Date max = TimeUtil.atEndOfDay(date);
        List<PaivanAteria> paivanAterias = paivanAteriaRepository.findBetweenDates(min, max);
        paivanAterias.forEach(pr -> {
            pr.setNimekkenNimi(nimekeRepository.findById(pr.getNimeke()).get().getNimi());
        });
        return paivanAterias;
    }

    @GetMapping("/randomize-test")
    List<Paiva> randomizeTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return viikkoListaRadomizer.randomizeList(calendar.getTime(), 7);
    }

    @GetMapping("/randomize")
    List<Paiva> randomize(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        AteriaParameterBuilder ateriaParameterBuilder = AteriaParameterBuilder.builder()
                .aamiainenAny(true)
                .lounasAny(true)
                .paivallinenAny(true)
                .illallinenAny(true)
                .build();

        return viikkoListaRadomizer.randomizeListWithTyyppi(TimeUtil.atStartOfDay(calendar.getTime()), 7, false, ateriaParameterBuilder);
    }

}
