package com.example.ruokaohjelma.ruokaohjelma.repository;

import com.example.ruokaohjelma.ruokaohjelma.enums.Tyyppi;
import com.example.ruokaohjelma.ruokaohjelma.model.Nimeke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NimekeRepository extends JpaRepository<Nimeke, Long> {

    @Query("SELECT n FROM Nimeke n WHERE n.tyyppi is not null AND n.tyyppi is not 4 order by function('RAND')")
    List<Nimeke> getRandomNimike();

    @Query("SELECT n FROM Nimeke n WHERE n.tyyppi = :tyyppi and n.tyyppi is not null  order by function('RAND')")
    List<Nimeke> getRandomNimekeByTyyppi(@Param("tyyppi") Tyyppi tyyppi);

    @Query("SELECT nimi FROM Nimeke WHERE id = :id")
    List<String> getNimiById(@Param("id") Long id);

    @Query("SELECT count(*) FROM Nimeke n WHERE n.tyyppi is not null AND n.tyyppi is not 4")
    long count();

    @Query("SELECT count(*) FROM Nimeke n WHERE n.tyyppi = :tyyppi and n.tyyppi is not null AND n.tyyppi is not 4")
    long getCountByTyyppi(@Param("tyyppi") Tyyppi t);

    @Query("SELECT n.id FROM Nimeke n WHERE n.tyyppi is null")
    long getNoDataNimeke();

    @Query("SELECT n FROM Nimeke n WHERE n.tyyppi is not 4")
    List<Nimeke> allNoRaakaaine();

    @Query("SELECT n FROM Nimeke n WHERE n.tyyppi = 4")
    List<Nimeke> getRuokaAine();


}
