package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.Direccion;
import cl.Ferramas.Ferramas.entity.EstadoPago;
import cl.Ferramas.Ferramas.services.DireccionService;
import cl.Ferramas.Ferramas.services.EstadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadopago")
public class EstadoPagoController {

    @Autowired
    private EstadoPagoService estadoPagoService;

    @GetMapping
    public List<EstadoPago> getAll() {
        return estadoPagoService.listarEstadoPagos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoPago> getById(@PathVariable Long id) {
        return estadoPagoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EstadoPago create(@RequestBody EstadoPago estadoPago) {
        return estadoPagoService.guardarEstadoPago(estadoPago);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (estadoPagoService.buscarPorId(id).isPresent()) {
            estadoPagoService.EliminarEstadoPago(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
