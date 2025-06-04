package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.ComunaDTO;
import cl.Ferramas.Ferramas.services.ComunaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comuna")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public List<ComunaDTO> getAll() {
        return comunaService.listaComunas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> comunaporId(@PathVariable Long id) {
        ComunaDTO comunaDTO = comunaService.buscarComunaporId(id);

        if (comunaDTO != null) {
            return ResponseEntity.ok(comunaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ComunaDTO> registrarcomuna(@Valid @RequestBody ComunaDTO comunaDTO) {
        ComunaDTO comuna = comunaService.guardarComuna(comunaDTO);
        notificarCambioCache(); // Invalida caché en Express
        return new ResponseEntity<>(comuna, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ComunaDTO comuna = comunaService.buscarComunaporId(id);

        if (comuna != null) {
            comunaService.eliminarComuna(id);
            notificarCambioCache(); // Invalida caché en Express
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método auxiliar para notificar a Express y limpiar la caché
    private void notificarCambioCache() {
        String url = "http://localhost:3000/cache/clear/ruta";

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("ruta", "/java-api/comuna");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("Caché invalidado en Express: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error al notificar a Express para limpiar caché: " + e.getMessage());
        }
    }
}
