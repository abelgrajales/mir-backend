package com.abelgrajales.mirmtz.repositories;

import com.abelgrajales.mirmtz.models.MirProposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MirPropositoRepository extends JpaRepository<MirProposito, Long> {

}
