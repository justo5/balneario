package com.balneario.Balneario.Mappers;

import com.balneario.Balneario.Entity.UnidadDeSombra;
import com.balneario.Balneario.DTOS.UnidadDeSombraRequest;
import com.balneario.Balneario.DTOS.UnidadDeSombraResponses;
import org.springframework.stereotype.Component;

@Component
public class UnidadDeSombraMapper {

    public UnidadDeSombraResponses toResponse(UnidadDeSombra unidadDeSombra) {
        UnidadDeSombraResponses response = new UnidadDeSombraResponses();
        response.setId(unidadDeSombra.getId());
        response.setTipo(unidadDeSombra.getTipo());
        response.setUbicacion(unidadDeSombra.getUbicacion());
        response.setPrecioPorDia(unidadDeSombra.getPrecioPorDia());
        response.setOcupada(unidadDeSombra.getOcupada());
        return response;
    }

    public UnidadDeSombra toEntity(UnidadDeSombraRequest request) {
        UnidadDeSombra unidadDeSombra = new UnidadDeSombra();
        unidadDeSombra.setTipo(request.getTipo());
        unidadDeSombra.setUbicacion(request.getUbicacion());
        unidadDeSombra.setPrecioPorDia(request.getPrecioPorDia());
        unidadDeSombra.setOcupada(request.getOcupada());
        return unidadDeSombra;
    }
}
