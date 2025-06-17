package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.CiudadDTO;
import cl.Ferramas.Ferramas.dto.RegionDTO;
import cl.Ferramas.Ferramas.entity.Ciudad;
import cl.Ferramas.Ferramas.mapper.CiudadMapper;
import cl.Ferramas.Ferramas.services.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;
    @Autowired
    private CiudadMapper ciudadMapper;

    @GetMapping
    public List<CiudadDTO> getAll() {

        return ciudadService.listarCiudades().stream().map(ciudadMapper::toDTO).collect(Collectors.toList());
    }




    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getById(@PathVariable Long id) {
        return ciudadService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PostMapping
    public Ciudad create(@RequestBody Ciudad ciudad) {
        Ciudad nueva = ciudadService.guardarCiudad(ciudad);
        notificarCambioCache(); // Invalida caché en Express
        return nueva;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ciudadService.buscarPorId(id).isPresent()) {
            ciudadService.eliminarCiudadPorId(id);
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
            body.put("ruta", "/java-api/ciudad");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("Caché invalidado en Express: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error al notificar a Express para limpiar caché: " + e.getMessage());
        }
    }
}
