package com.balneario.Balneario.DTOS;

import com.balneario.Balneario.Entity.Cliente;
import com.balneario.Balneario.Entity.Reserva;
import com.balneario.Balneario.Entity.UnidadDeSombra;
import lombok.Data;
import java.sql.Date;


@Data
public class ReservaResponses extends Reserva {
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private UnidadDeSombra unidadDeSombra;
    private Cliente cliente;
}
