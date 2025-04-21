package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.entity.Comuna;
import cl.Ferramas.Ferramas.entity.DetallePedido;
import cl.Ferramas.Ferramas.services.ComunaService;
import cl.Ferramas.Ferramas.services.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallepedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping
    public List<DetallePedido> getAll() {
        return detallePedidoService.buscarTodosLosDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> getById(@PathVariable Long id) {
        return detallePedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetallePedido create(@RequestBody DetallePedido detallePedido) {
        return detallePedidoService.guardarDetalle(detallePedido);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (detallePedidoService.buscarPorId(id).isPresent()) {
            detallePedidoService.eliminarDetallePedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
