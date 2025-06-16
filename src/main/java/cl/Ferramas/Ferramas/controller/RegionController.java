package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.RegionDTO;
import cl.Ferramas.Ferramas.entity.Region;
import cl.Ferramas.Ferramas.mapper.RegionMapper;
import cl.Ferramas.Ferramas.services.RegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionMapper regionMapper;

    @GetMapping
    public List<RegionDTO> getAll() {
        return regionService.ListarRegiones()
                            .stream()
                            .map(regionMapper::toDTO)
                            .collect(Collectors.toList()); // Conversion a DTO
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> getById(@PathVariable Long id) {
        return regionService.BuscarRegionPorId(id)
                .map(region -> ResponseEntity.ok(regionMapper.toDTO(region)))
                .orElse(ResponseEntity.notFound().build()); // Conversion a DTO
    }

    @PostMapping
    public Region create(@RequestBody Region region) {
        Region nueva = regionService.guardarRegion(region);
        notificarCambioCache();
        return nueva;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (regionService.BuscarRegionPorId(id).isPresent()) {
            regionService.EliminarRegion(id);
            notificarCambioCache();
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
            body.put("ruta", "/java-api/region");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("Caché invalidado en Express: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error al notificar a Express para limpiar caché: " + e.getMessage()); 
        }
    }
}
