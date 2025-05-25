package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.entity.Pago;
import cl.Ferramas.Ferramas.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public List<Pago> getAll() {
        return pagoService.ListarPagos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getById(@PathVariable Long id) {
        return pagoService.BuscarPagoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pago create(@RequestBody Pago pago) {
        return pagoService.guardarPago(pago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (pagoService.BuscarPagoPorId(id).isPresent()) {
            pagoService.EliminarPago(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/actualizarestado/{id}")
    public ResponseEntity<String> actualizarEstadoPago(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        if (!body.containsKey("estadoPagoId")) {
            return ResponseEntity.badRequest().body("Falta el campo 'estadoPagoId'");
        }

        try {
            Long estadoId = Long.valueOf(body.get("estadoPagoId").toString());
            boolean actualizado = pagoService.actualizarEstadoPago(id, estadoId);
            if (actualizado) {
                return ResponseEntity.ok("Estado del pago actualizado correctamente.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al actualizar estado: " + e.getMessage());
        }
    }
}
