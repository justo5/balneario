package com.balneario.Balneario.Controller;

import com.balneario.Balneario.DTOS.ClienteRequest;
import com.balneario.Balneario.DTOS.ClienteResponses;
import com.balneario.Balneario.Entity.Cliente;
import com.balneario.Balneario.Entity.UnidadDeSombra;
import com.balneario.Balneario.Entity.Reserva;
import com.balneario.Balneario.Mappers.ClienteMapper;
import com.balneario.Balneario.Services.ClienteService;
import com.balneario.Balneario.Services.UnidadDeSombraService;
import com.balneario.Balneario.Services.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private UnidadDeSombraService unidadDeSombraService;

    @Autowired
    private ReservaService reservaService;

    // Obtener todos los clientes
    @GetMapping
    public List<ClienteResponses> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return clientes.stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    // Crear cliente
    @PostMapping("/create")
    public ResponseEntity<ClienteResponses> crearCliente(@RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.toEntity(clienteRequest);
        Cliente savedCliente = clienteService.save(cliente);
        ClienteResponses response = clienteMapper.toResponse(savedCliente);
        return ResponseEntity.ok(response);
    }

    // Obtener cliente por ID
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<ClienteResponses> obtenerClientePorId(@PathVariable int id) {
        Cliente cliente = clienteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return ResponseEntity.ok(clienteMapper.toResponse(cliente));
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable int id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Reservar una unidad de sombra
    @PostMapping("/{idCliente}/unidad/{idUnidad}/reservar")
    public ResponseEntity<String> reservarUnidad(
            @PathVariable int idCliente,
            @PathVariable int idUnidad,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {

        Optional<Cliente> clienteOpt = clienteService.findById(idCliente);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(404).body("No se encontró un cliente con ID: " + idCliente);
        }

        Optional<UnidadDeSombra> unidadOpt = unidadDeSombraService.findById(idUnidad);
        if (unidadOpt.isEmpty()) {
            return ResponseEntity.status(404).body("No se encontró una unidad de sombra con ID: " + idUnidad);
        }

        UnidadDeSombra unidad = unidadOpt.get();
        if (unidad.isOcupada()) {
            return ResponseEntity.status(400).body("La unidad de sombra ya está reservada en este periodo");
        }

        // Crear la reserva
        Reserva reserva = new Reserva(
                clienteOpt.get(),
                unidad,
                Date.valueOf(LocalDate.parse(fechaInicio)),
                Date.valueOf(LocalDate.parse(fechaFin)),
                "Pendiente"
        );


         //Marcar unidad como ocupada
        unidad.setOcupada(true);
        unidadDeSombraService.save(unidad);

        // Guardar reserva
        reservaService.save(reserva);

        return ResponseEntity.ok("Reserva realizada con éxito");
    }
}
