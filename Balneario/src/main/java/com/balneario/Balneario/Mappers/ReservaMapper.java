package com.balneario.Balneario.Mappers;

import com.balneario.Balneario.Entity.UnidadDeSombra;
import com.balneario.Balneario.Entity.Cliente;
import com.balneario.Balneario.Entity.Reserva;
import com.balneario.Balneario.DTOS.ReservaRequest;
import com.balneario.Balneario.DTOS.ReservaResponses;
import org.springframework.stereotype.Component;
import java.sql.Date;


@Component
public class ReservaMapper {

    public ReservaResponses toResponse(Reserva reserva) {
        ReservaResponses response = new ReservaResponses();
        response.setId(reserva.getId());
        response.setFechaInicio(reserva.getFechaInicio());
        response.setFechaFin(reserva.getFechaFin());
        response.setEstado(reserva.getEstado());
        response.setUnidadDeSombra(reserva.getUnidadDeSombra());
        response.setCliente(reserva.getCliente());
        return response;
    }

    public Reserva toEntity(ReservaRequest request, UnidadDeSombra unidad, Cliente cliente) {
        Reserva reserva = new Reserva();
        reserva.setId(request.getId());
        reserva.setFechaInicio(new java.sql.Date(request.getFechaInicio().getTime()));
        reserva.setFechaFin(new java.sql.Date(request.getFechaFin().getTime()));

        // Conversión Boolean -> String
        reserva.setEstado(request.getEstado() != null && request.getEstado() ? "Confirmada" : "Pendiente");

        reserva.setUnidadDeSombra(unidad);
        reserva.setCliente(cliente);
        return reserva;
    }
}
