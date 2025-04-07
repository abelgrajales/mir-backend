package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.Eje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjeRepository extends JpaRepository<Eje, Long> {
}
