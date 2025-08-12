package com.balneario.Balneario.Mappers;


import com.balneario.Balneario.Entity.Cliente;
import com.balneario.Balneario.DTOS.ClienteRequest;
import com.balneario.Balneario.DTOS.ClienteResponses;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteResponses toResponse(Cliente cliente) {
        ClienteResponses response = new ClienteResponses();
        response.setId(cliente.getId());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());
        response.setDni(cliente.getDni());
        response.setTelefono(cliente.getTelefono());
        response.setEmail(cliente.getEmail());
        return response;
    }

    public Cliente toEntity(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setDni(request.getDni());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        return cliente;
    }
}