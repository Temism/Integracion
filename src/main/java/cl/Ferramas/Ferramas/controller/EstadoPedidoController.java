package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.EstadoPago;
import cl.Ferramas.Ferramas.entity.EstadoPedido;
import cl.Ferramas.Ferramas.services.EstadoPagoService;
import cl.Ferramas.Ferramas.services.EstadoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadopedido")
public class EstadoPedidoController {

    @Autowired
    private EstadoPedidoService estadoPedidoService;

    @GetMapping
    public List<EstadoPedido> getAll() {
        return estadoPedidoService.listarEstadoPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoPedido> getById(@PathVariable Long id) {
        return estadoPedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EstadoPedido create(@RequestBody EstadoPedido estadopedido) {
        return estadoPedidoService.guardarEstadoPedido(estadopedido);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (estadoPedidoService.buscarPorId(id).isPresent()) {
            estadoPedidoService.EliminarEstadoPedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
