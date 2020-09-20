package com.example.ruokaohjelma.ruokaohjelma.repository;

import com.example.ruokaohjelma.ruokaohjelma.model.PaivanAteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface PaivanAteriaRepository extends JpaRepository<PaivanAteria, Long> {

    @Query("FROM PaivanAteria WHERE ajanKohta >= :min and ajanKohta <= :max")
    List<PaivanAteria> findBetweenDates(@Param("min")Date min, @Param("max")Date max);

    @Query("FROM PaivanAteria WHERE id in (:ids)")
    List<PaivanAteria> findByIds(@Param("ids")Collection<Long> ids);

}
