package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.HistorialPrecioDTO;
import cl.Ferramas.Ferramas.entity.HistorialPrecio;
import cl.Ferramas.Ferramas.mapper.HistorialPrecioMapper;
import cl.Ferramas.Ferramas.services.HistorialPrecioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/historialprecio")
public class HistorialPrecioController {

    @Autowired
    private HistorialPrecioService historialPrecioService;

    @GetMapping
    public ResponseEntity<List<HistorialPrecioDTO>> getAll() {
        List<HistorialPrecioDTO> historial = historialPrecioService.listaHistorialPrecio()
                .stream()
                .map(HistorialPrecioMapper::toDTO)
                .toList();

        return ResponseEntity.ok(historial);
    }

    @PostMapping
    public ResponseEntity<HistorialPrecioDTO> crearHistorial(@Valid @RequestBody HistorialPrecio historial) {
        HistorialPrecio creado = historialPrecioService.guardarHistorialPrecio(historial);
        notificarCambioCache(); // Limpia caché de Express

        return new ResponseEntity<>(HistorialPrecioMapper.toDTO(creado), HttpStatus.CREATED);
    }

    // Método auxiliar para limpiar caché en Express
    private void notificarCambioCache() {
        String url = "http://localhost:3000/cache/clear/ruta";

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("ruta", "/java-api/historialprecio");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("Caché invalidado en Express: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error al notificar a Express para limpiar caché: " + e.getMessage());
        }
    }
}
