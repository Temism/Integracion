package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.TipoMovimiento;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.TipoMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipomov")
public class TipoMovimientoController {

    @Autowired
    private TipoMovimientoService tipoMovimientoService;

    @GetMapping
    public List<TipoMovimiento> getAll() {
        return tipoMovimientoService.ListarMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMovimiento> getById(@PathVariable Long id) {
        return tipoMovimientoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoMovimiento create(@RequestBody TipoMovimiento Tipo) {
        return tipoMovimientoService.guardarTipoMov(Tipo);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tipoMovimientoService.buscarPorId(id).isPresent()) {
            tipoMovimientoService.eliminarTipoMov(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
