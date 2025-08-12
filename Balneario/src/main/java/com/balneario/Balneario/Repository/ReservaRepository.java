package com.balneario.Balneario.Repository;

import com.balneario.Balneario.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByFechaInicio(Date fechaInicio);

    List<Reserva> findByFechaFin(Date fechaFin);

    List<Reserva> findByEstado(String estado);
}
