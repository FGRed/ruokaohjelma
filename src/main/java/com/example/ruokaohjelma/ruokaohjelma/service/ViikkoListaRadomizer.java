package com.example.ruokaohjelma.ruokaohjelma.service;

import com.example.ruokaohjelma.ruokaohjelma.AteriaParameterBuilder;
import com.example.ruokaohjelma.ruokaohjelma.enums.Tyyppi;
import com.example.ruokaohjelma.ruokaohjelma.model.Nimeke;
import com.example.ruokaohjelma.ruokaohjelma.model.PaivanAteria;
import com.example.ruokaohjelma.ruokaohjelma.model.Paiva;
import com.example.ruokaohjelma.ruokaohjelma.repository.PaivanAteriaRepository;
import com.example.ruokaohjelma.ruokaohjelma.repository.NimekeRepository;
import com.example.ruokaohjelma.ruokaohjelma.repository.PaivaRepository;
import com.example.ruokaohjelma.ruokaohjelma.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ViikkoListaRadomizer {
    private static final Logger log = LoggerFactory.getLogger(ViikkoListaRadomizer.class);

    NimekeRepository nimekeRepository;
    PaivanAteriaRepository paivanAteriaRepository;
    PaivaRepository paivaRepository;

    public ViikkoListaRadomizer(NimekeRepository nimekeRepository, PaivanAteriaRepository paivanAteriaRepository, PaivaRepository paivaRepository){
        this.nimekeRepository = nimekeRepository;
        this.paivanAteriaRepository = paivanAteriaRepository;
        this.paivaRepository = paivaRepository;


    }


    public List<Paiva> randomizeList(Date alkupvm, int dayAmount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(alkupvm);
        List<Paiva> paivas = new ArrayList<>();
        for (int i = 0; i < dayAmount; i++) {

            Paiva paiva = new Paiva();
            paiva.setPvm(calendar.getTime());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(calendar.getTime());
            calendar2.set(Calendar.HOUR, 8);
            List<PaivanAteria> paivanAterias = new ArrayList<>();
            for (int j = 0; j < 3; j++){
                PaivanAteria paivanAteria = new PaivanAteria();
                paivanAteria.setNimeke(nimekeRepository.getRandomNimike().get(0).getId());
                paivanAteria.setAjanKohta(calendar2.getTime());
                calendar2.add(Calendar.HOUR, 8);

                paivanAteriaRepository.save(paivanAteria);
                paivanAterias.add(paivanAteria);
            }
            paiva.setPaivanAteriaIds(paivanAterias.stream().map(PaivanAteria::getId).collect(Collectors.toList()));
            paiva.setPaivanAteriat(paivanAterias);
            paivas.add(paiva);
            paivaRepository.save(paiva);
            log.info("Päivällä: "+paiva.getId()+", index: " + i + " on "+paiva.getPaivanAteriat().size() + " listan jäsentä.");


            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return paivas;
    }

    public List<Paiva> randomizeListWithTyyppi(Date alkupvm, int paivienMaara, boolean salliSamatSamalleViikolle, AteriaParameterBuilder ateriaParameterBuilder){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(alkupvm);
        List<Paiva> paivas = new ArrayList<>();

        List<Long> lisatytAaamiaiset = new ArrayList<>();
        List<Long> lisatytPaivalliset = new ArrayList<>();
        List<Long> lisatytIltapalat = new ArrayList<>();
        List<Long> lisatytLounaat = new ArrayList<>();

        final long AAMIAISTEN_MAARA = nimekeRepository.getCountByTyyppi(Tyyppi.AAMIANEN);
        final long PAIVALLISTEN_MAARA = nimekeRepository.getCountByTyyppi(Tyyppi.PAIVALLINEN);
        final long LOUNAITTEN_MAARA = nimekeRepository.getCountByTyyppi(Tyyppi.LOUNAS);
        final long ILLALLISTEN_MAARA = nimekeRepository.getCountByTyyppi(Tyyppi.ILLALLINEN);

        for (int i = 0; i < paivienMaara; i++) {
            List<PaivanAteria> paivanAterias = new ArrayList<>();
            Paiva paiva = new Paiva();
            paiva.setPvm(calendar.getTime());
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(calendar.getTime());

            if(i % 7 == 0){
                lisatytAaamiaiset.clear();
                lisatytPaivalliset.clear();
                lisatytIltapalat.clear();
            }

            //AAMIAINEN
            calendar2.set(Calendar.HOUR, 8);
            PaivanAteria aamiainen = generateRandomAteriaByTyyppi(lisatytAaamiaiset, AAMIAISTEN_MAARA,Tyyppi.AAMIANEN, calendar2.getTime(), salliSamatSamalleViikolle, ateriaParameterBuilder.isAamiainenAny());
            paivanAterias.add(aamiainen);
            paivanAteriaRepository.save(aamiainen);
            //


            //PAIVALLINEN
            calendar2.add(Calendar.HOUR, 3);
            calendar2.add(Calendar.MINUTE, 20);
            PaivanAteria paivallinen = generateRandomAteriaByTyyppi(lisatytPaivalliset, PAIVALLISTEN_MAARA, Tyyppi.PAIVALLINEN, calendar2.getTime(), salliSamatSamalleViikolle, ateriaParameterBuilder.isPaivallinenAny());
            paivanAterias.add(paivallinen);
            paivanAteriaRepository.save(paivallinen);
            //


            //LOUNAS
            calendar2.add(Calendar.HOUR, 3);
            calendar2.add(Calendar.MINUTE, 20);
            PaivanAteria lounas = generateRandomAteriaByTyyppi(lisatytLounaat, LOUNAITTEN_MAARA, Tyyppi.LOUNAS, calendar2.getTime(), salliSamatSamalleViikolle, ateriaParameterBuilder.isLounasAny());
            paivanAterias.add(lounas);
            paivanAteriaRepository.save(lounas);
            //

            //LOUNAS2
            calendar2.add(Calendar.HOUR, 3);
            calendar2.add(Calendar.MINUTE, 20);
            PaivanAteria lounas2 = generateRandomAteriaByTyyppi(lisatytLounaat, LOUNAITTEN_MAARA, Tyyppi.LOUNAS, calendar2.getTime(), salliSamatSamalleViikolle, ateriaParameterBuilder.isLounasAny());
            paivanAterias.add(lounas2);
            paivanAteriaRepository.save(lounas2);
            //


            //ILLALLINEN
            calendar2.add(Calendar.HOUR, 3);
            calendar2.add(Calendar.MINUTE, 20);
            PaivanAteria illallinen = generateRandomAteriaByTyyppi(lisatytIltapalat, ILLALLISTEN_MAARA, Tyyppi.ILLALLINEN, calendar2.getTime(), salliSamatSamalleViikolle, ateriaParameterBuilder.isIllallinenAny());
            paivanAterias.add(illallinen);
            paivanAteriaRepository.save(illallinen);
            //


            paiva.setPaivanAteriaIds(paivanAterias.stream().map(PaivanAteria::getId).collect(Collectors.toList()));
            paiva.setPaivanAteriat(paivanAterias);
            paivas.add(paiva);
            paiva.setPvmNimi(TimeUtil.getPaivaNimi(paiva.getPvm()));

            paivaRepository.save(paiva);

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return paivas;
    }

    private PaivanAteria generateRandomAteriaByTyyppi(List<Long> nimekkeet, Long maxNimekkeidenMaara, Tyyppi t, Date ajanKohta, boolean salliSamatSamalleViikolle, boolean kaytaTyyppia){


        if(!kaytaTyyppia){
            maxNimekkeidenMaara = nimekeRepository.count();
        }

        PaivanAteria pr = new PaivanAteria();
        pr.setAjanKohta(ajanKohta);
        pr.setKellonaika(TimeUtil.getKloAika(ajanKohta));

        Long n0 = kaytaTyyppia ? nimekeRepository.getRandomNimekeByTyyppi(t).get(0).getId() : nimekeRepository.getRandomNimike().get(0).getId();
        if(!salliSamatSamalleViikolle) {
            if (!nimekkeet.contains(n0)) {
                nimekkeet.add(n0);
                pr.setNimeke(n0);
            } else {
                if (nimekkeet.size() < maxNimekkeidenMaara) {
                    while (nimekkeet.contains(n0)) {
                        n0 = kaytaTyyppia ? nimekeRepository.getRandomNimekeByTyyppi(t).get(0).getId() : nimekeRepository.getRandomNimike().get(0).getId();
                    }
                    nimekkeet.add(n0);
                } else {
                    n0 = -1L;
                }
            }
        }
        pr.setNimeke(n0);
        if(n0 != -1L) {
            pr.setNimekkenNimi(nimekeRepository.getNimiById(n0).get(0));
        }
        return pr;
    }

}
