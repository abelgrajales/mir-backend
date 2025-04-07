package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.CentroGestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroGestorRepository extends JpaRepository<CentroGestor, Long> {
}
