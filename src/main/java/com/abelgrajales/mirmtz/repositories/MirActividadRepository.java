package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.MirActividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MirActividadRepository extends JpaRepository<MirActividad, Long> {

}
