package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.Estrategia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstrategiaRepository extends JpaRepository<Estrategia, Long> {

}
