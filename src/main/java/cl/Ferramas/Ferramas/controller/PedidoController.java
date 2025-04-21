package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.entity.Pedido;

import cl.Ferramas.Ferramas.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> getAll() {
        return pedidoService.ListarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id) {
        return pedidoService.BuscarPedidoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido create(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (pedidoService.BuscarPedidoPorId(id).isPresent()) {
            pedidoService.EliminarPedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
