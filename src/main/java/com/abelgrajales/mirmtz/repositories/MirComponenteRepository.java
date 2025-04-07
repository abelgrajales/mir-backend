package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.MirComponente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MirComponenteRepository extends JpaRepository<MirComponente, Long> {
}
