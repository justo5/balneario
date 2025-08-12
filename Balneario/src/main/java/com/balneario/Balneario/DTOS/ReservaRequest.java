package com.balneario.Balneario.DTOS;

import lombok.Data;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ReservaRequest {
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean estado;
    private int unidadDeSombraId;
    private int clienteId;
}
