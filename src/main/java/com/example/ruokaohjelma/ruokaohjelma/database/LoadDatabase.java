package com.example.ruokaohjelma.ruokaohjelma.database;

import com.example.ruokaohjelma.ruokaohjelma.enums.Tyyppi;
import com.example.ruokaohjelma.ruokaohjelma.model.Nimeke;
import com.example.ruokaohjelma.ruokaohjelma.repository.NimekeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase1(NimekeRepository nimekeRepository){

        return args -> {

            Nimeke nimeke0 = new Nimeke();
            nimeke0.setNimi("Mannapuuro");
            nimeke0.setTyyppi(Tyyppi.AAMIANEN);
            log.info("Preloading " + nimekeRepository.save(nimeke0));

            Nimeke nimeke0_1 = new Nimeke();
            nimeke0_1.setNimi("Mysli");
            nimeke0_1.setTyyppi(Tyyppi.AAMIANEN);
            log.info("Preloading " + nimekeRepository.save(nimeke0_1));

            Nimeke nimeke0_2 = new Nimeke();
            nimeke0_2.setNimi("Banaani");
            nimeke0_2.setTyyppi(Tyyppi.AAMIANEN);
            log.info("Preloading " + nimekeRepository.save(nimeke0_2));


            Nimeke nimeke1 = new Nimeke();
            nimeke1.setNimi("Jauhenlihakeitto");
            nimeke1.setTyyppi(Tyyppi.PAIVALLINEN);
            log.info("Preloading " + nimekeRepository.save(nimeke1));

            Nimeke nimeke2 = new Nimeke();
            nimeke2.setNimi("Voileipää");
            nimeke2.setTyyppi(Tyyppi.ILLALLINEN);
            log.info("Preloading " + nimekeRepository.save(nimeke2));

            Nimeke nimeke3 = new Nimeke();
            nimeke3.setNimi("Kolmen viljan puuro");
            nimeke3.setTyyppi(Tyyppi.AAMIANEN);
            log.info("Preloading " + nimekeRepository.save(nimeke3));

            Nimeke nimeke4 = new Nimeke();
            nimeke4.setNimi("Pasta Bolognese");
            nimeke4.setTyyppi(Tyyppi.PAIVALLINEN);
            log.info("Preloading " + nimekeRepository.save(nimeke4));

            Nimeke nimeke5 = new Nimeke();
            nimeke5.setNimi("Hernekeitto");
            nimeke5.setTyyppi(Tyyppi.ILLALLINEN);
            log.info("Preloading " + nimekeRepository.save(nimeke4));

            Nimeke nimeke6 = new Nimeke();
            nimeke6.setNimi("Data loppui kesken!");
            nimekeRepository.save(nimeke6);

            Nimeke nimeke7 = new Nimeke();
            nimeke7.setNimi("Pizza");
            nimeke7.setTyyppi(Tyyppi.PAIVALLINEN);
            log.info("Preloading " + nimekeRepository.save(nimeke7));

            Nimeke nimeke8 = new Nimeke();
            nimeke8.setNimi("Hamppari");
            nimeke8.setTyyppi(Tyyppi.PAIVALLINEN);
            log.info("Preloading " + nimekeRepository.save(nimeke8));

            Nimeke nimeke9 = new Nimeke();
            nimeke9.setNimi("Intialainen ruoka");
            nimeke9.setTyyppi(Tyyppi.PAIVALLINEN);
            log.info("Preloading " + nimekeRepository.save(nimeke9));

            Nimeke nimeke10= new Nimeke();
            nimeke10.setNimi("Ruoka 1");
            nimeke10.setTyyppi(Tyyppi.LOUNAS);
            log.info("Preloading " + nimekeRepository.save(nimeke10));

            Nimeke nimeke11= new Nimeke();
            nimeke11.setNimi("Ruoka 2");
            nimeke11.setTyyppi(Tyyppi.LOUNAS);
            log.info("Preloading " + nimekeRepository.save(nimeke11));

            Nimeke nimeke12 = new Nimeke();
            nimeke12.setNimi("Ruoka 3");
            nimeke10.setTyyppi(Tyyppi.LOUNAS);
            log.info("Preloading " + nimekeRepository.save(nimeke12));


        };
    }

   /* @Bean
    CommandLineRunner initDatabase2(ReseptiRepository reseptiRepository){
        return args -> {
            Resepti resepti = new Resepti();
            List<Long> ainesOsat = new ArrayList();
            ainesOsat.add(1L);
            resepti.setAinesosat(ainesOsat);
            log.info("Preloading " + reseptiRepository.save(resepti));
        };
    }

   / @Bean
    CommandLineRunner initDatabase3(PaivaRepository paivaRepository){
        return args -> {
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i <=8; i++){
                Paiva paiva = new Paiva();
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                paiva.setPvm(calendar.getTime());
                log.info("Preloading " + paivaRepository.save(paiva));
            }

        };
    }

    @Bean
    CommandLineRunner initDatabase4(PaivanRuokaRepository paivanRuokaReposiF){
        return args -> {
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i <=25; i++){
                if (i % 3 == 0){
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
                calendar.add(Calendar.HOUR, 7);
                ListanJasen listanJasen = new ListanJasen();
                listanJasen.setAjanKohta(calendar.getTime());
                listanJasen.setNimeke(1);
                listanJasenRepository.save(listanJasen);

            }


        };
    }*/

}
