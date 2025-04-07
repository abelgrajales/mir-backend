package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.MirFin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MirFinRepository extends JpaRepository<MirFin, Long> {
}
