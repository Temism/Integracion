package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.entity.HistorialEstadoPedido;

import cl.Ferramas.Ferramas.services.HistorialEstadosPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historialestado")
public class HistorialEstadoPedidoController {

    @Autowired
    private HistorialEstadosPedidoService historialEstadosPedidoService;

    @GetMapping
    public List<HistorialEstadoPedido> getAll() {
        return historialEstadosPedidoService.listaHistorialEstadoPedido();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialEstadoPedido> getById(@PathVariable Long id) {
        return historialEstadosPedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HistorialEstadoPedido create(@RequestBody HistorialEstadoPedido historial) {
        return historialEstadosPedidoService.guardarHistorial(historial);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (historialEstadosPedidoService.buscarPorId(id).isPresent()) {
            historialEstadosPedidoService.ELiminarHistorial(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
