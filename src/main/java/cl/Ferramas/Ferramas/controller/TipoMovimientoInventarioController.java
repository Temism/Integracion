package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.TipoMovimientoInventario;
import cl.Ferramas.Ferramas.services.TipoMovimientoInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipomov")
public class TipoMovimientoInventarioController {

    @Autowired
    private TipoMovimientoInventarioService tipoMovimientoService;

    @GetMapping
    public List<TipoMovimientoInventario> getAll() {
        return tipoMovimientoService.ListarMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoMovimientoInventario> getById(@PathVariable Long id) {
        return tipoMovimientoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoMovimientoInventario create(@RequestBody TipoMovimientoInventario Tipo) {
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
