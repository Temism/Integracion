package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.MovimientoInventario;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.MovimientoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientoinventario")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;

    @GetMapping
    public List<MovimientoInventario> getAll() {
        return movimientoInventarioService.BuscarTodosLosMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> getById(@PathVariable Long id) {
        return movimientoInventarioService.BuscarMovimientoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MovimientoInventario create(@RequestBody MovimientoInventario mov) {
        return movimientoInventarioService.guardarMovimientoInventario(mov);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (movimientoInventarioService.BuscarMovimientoPorId(id).isPresent()) {
            movimientoInventarioService.EliminarMovimientoInventario(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
