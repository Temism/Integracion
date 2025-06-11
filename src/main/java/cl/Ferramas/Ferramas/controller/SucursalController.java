package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.entity.Sucursal;
import cl.Ferramas.Ferramas.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> getAll() {
        return sucursalService.BuscarTodasSucursales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> getById(@PathVariable Long id) {
        return sucursalService.BuscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sucursal create(@RequestBody Sucursal sucursal) {
        Sucursal nueva = sucursalService.guardarSucursal(sucursal);
        notificarCambioCache(); // Invalida caché en Express
        return nueva;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (sucursalService.BuscarPorId(id).isPresent()) {
            sucursalService.EliminarSucursal(id);
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
            body.put("ruta", "/java-api/sucursal");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("Caché invalidado en Express: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error al notificar a Express para limpiar caché: " + e.getMessage());
        }
    }
}
