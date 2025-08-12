package com.balneario.Balneario.Repository;

import com.balneario.Balneario.Entity.UnidadDeSombra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadDeSombraRepository extends JpaRepository<UnidadDeSombra, Integer> {

    List<UnidadDeSombra> findByTipo(String tipo);

    List<UnidadDeSombra> findByOcupada(Boolean ocupada);
}
