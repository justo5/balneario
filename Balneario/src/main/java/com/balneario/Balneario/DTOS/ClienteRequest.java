package com.balneario.Balneario.DTOS;

import lombok.Data;

@Data
public class ClienteRequest {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
}
