package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {
}
