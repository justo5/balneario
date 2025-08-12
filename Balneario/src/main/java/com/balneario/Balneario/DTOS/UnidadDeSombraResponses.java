package com.balneario.Balneario.DTOS;

import lombok.Data;

@Data
public class UnidadDeSombraResponses {
    private int id;
    private String tipo;
    private String ubicacion;
    private Double precioPorDia;
    private Boolean ocupada;
}
