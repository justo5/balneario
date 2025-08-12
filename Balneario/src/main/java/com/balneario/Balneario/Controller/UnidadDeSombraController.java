package com.balneario.Balneario.Controller;

import com.balneario.Balneario.DTOS.UnidadDeSombraRequest;
import com.balneario.Balneario.DTOS.UnidadDeSombraResponses;
import com.balneario.Balneario.Entity.UnidadDeSombra;
import com.balneario.Balneario.Mappers.UnidadDeSombraMapper;
import com.balneario.Balneario.Services.UnidadDeSombraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/unidades")  // Endpoint más claro para unidades de sombra
public class UnidadDeSombraController {

    @Autowired
    private UnidadDeSombraService unidadDeSombraService;

    @Autowired
    private UnidadDeSombraMapper unidadDeSombraMapper;

    @PostMapping("/create")
    public ResponseEntity<UnidadDeSombraResponses> crearUnidad(@RequestBody UnidadDeSombraRequest unidadDeSombraRequest) {
        UnidadDeSombra unidadDeSombra = unidadDeSombraMapper.toEntity(unidadDeSombraRequest);
        UnidadDeSombra savedUnidad = unidadDeSombraService.save(unidadDeSombra);
        UnidadDeSombraResponses response = unidadDeSombraMapper.toResponse(savedUnidad);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<UnidadDeSombraResponses> obtenerUnidadPorId(@PathVariable Integer id) {
        UnidadDeSombra unidad = unidadDeSombraService.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad de sombra no encontrada"));
        UnidadDeSombraResponses response = unidadDeSombraMapper.toResponse(unidad);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUnidad(@PathVariable Integer id) {
        unidadDeSombraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UnidadDeSombraResponses>> obtenerTodasLasUnidades() {
        List<UnidadDeSombra> unidades = unidadDeSombraService.findAll();
        List<UnidadDeSombraResponses> responses = unidades.stream()
                .map(unidadDeSombraMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }
}
