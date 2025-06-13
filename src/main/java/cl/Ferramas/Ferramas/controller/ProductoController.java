package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.ProductoDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;
import cl.Ferramas.Ferramas.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<RegistroProductoDTO> registrarproducto(@Valid @RequestBody RegistroProductoDTO registroDTO) {
        RegistroProductoDTO productoregistrado = productoService.registrarproducto(registroDTO);
        notificarCambioCache(); // Invalida caché en Express
        return new ResponseEntity<>(productoregistrado, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> productoporId(@PathVariable Long id) {
        ProductoDTO productoDTO = productoService.buscarprodporId(id);

        if (productoDTO != null) {
            return ResponseEntity.ok(productoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<ProductoDTO> getAll() {
        return productoService.listarProductos();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RegistroProductoDTO> actualizarproducto(@PathVariable Long id, @RequestBody RegistroProductoDTO productoDTO){

        RegistroProductoDTO producto = productoService.actualizarProducto(id, productoDTO);

        if (producto != null) {
            notificarCambioCache(); // Invalida caché en Express
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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
            body.put("ruta", "/java-api/producto");

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println("Caché invalidado en Express: " + response.getBody());

        } catch (Exception e) {
            System.err.println("Error al notificar a Express para limpiar caché: " + e.getMessage());
        }
    }
}
