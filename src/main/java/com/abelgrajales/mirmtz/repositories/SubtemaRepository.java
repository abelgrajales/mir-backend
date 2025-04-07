package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.Subtema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtemaRepository extends JpaRepository<Subtema, Long> {
}
