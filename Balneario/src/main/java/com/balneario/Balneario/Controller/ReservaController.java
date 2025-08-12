package com.balneario.Balneario.Controller;

import com.balneario.Balneario.DTOS.ReservaRequest;
import com.balneario.Balneario.DTOS.ReservaResponses;
import com.balneario.Balneario.Entity.Cliente;
import com.balneario.Balneario.Entity.Reserva;
import com.balneario.Balneario.Entity.UnidadDeSombra;
import com.balneario.Balneario.Mappers.ReservaMapper;
import com.balneario.Balneario.Services.ClienteService;
import com.balneario.Balneario.Services.UnidadDeSombraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.balneario.Balneario.Services.ReservaService;

import java.util.List;

//import java.util.Optional;
@RestController
@RequestMapping("/solicitudes")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private ReservaMapper reservaMapper;

    @Autowired
    private UnidadDeSombraService unidadDeSombraService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/create")
    public ResponseEntity<ReservaResponses> crearSolicitud(@RequestBody ReservaRequest reservaRequest) {
        UnidadDeSombra unidad = unidadDeSombraService.findById(reservaRequest.getUnidadDeSombraId())
                .orElseThrow(() -> new RuntimeException("Unidad de sombra no encontrada"));

        Cliente cliente = clienteService.findById(reservaRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Reserva reserva = reservaMapper.toEntity(reservaRequest, unidad, cliente);
        Reserva savedReserva = reservaService.save(reserva);
        ReservaResponses response = reservaMapper.toResponse(savedReserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<ReservaResponses> obtenerSolicitudPorId(@PathVariable Integer id) {
        Reserva reserva = reservaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        ReservaResponses solicitudObtenida = reservaMapper.toResponse(reserva);
        return ResponseEntity.ok(solicitudObtenida);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Integer id) {
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponses>> obtenerTodasLasSolicitudes() {
        List<Reserva> reservas = reservaService.findAll();
        List<ReservaResponses> respuestas = reservas.stream()
                .map(reservaMapper::toResponse)
                .toList();
        return ResponseEntity.ok(respuestas);
    }
}
