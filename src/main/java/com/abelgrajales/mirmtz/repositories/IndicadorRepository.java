package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndicadorRepository extends JpaRepository<Indicador, Long> {
    @Query("SELECT i FROM Indicador i WHERE i.mirFin.id = ?1")
    List<Indicador> findByMirFin(Long id);

    @Query("SELECT i FROM Indicador i WHERE i.mirProposito.id = ?1")
    List<Indicador> findByMirProposito(Long id);
}
