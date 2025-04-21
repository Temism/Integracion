package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.ReferenciaMovimiento;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.ReferenciaMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referenciamov")
public class ReferenciaMovimientoController {

    @Autowired
    private ReferenciaMovimientoService referenciaMovimientoService;

    @GetMapping
    public List<ReferenciaMovimiento> getAll() {
        return referenciaMovimientoService.ListarReferenciasMov();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReferenciaMovimiento> getById(@PathVariable Long id) {
        return referenciaMovimientoService.BuscarRefMovPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ReferenciaMovimiento create(@RequestBody ReferenciaMovimiento ref) {
        return referenciaMovimientoService.GuardarRef(ref);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (referenciaMovimientoService.BuscarRefMovPorId(id).isPresent()) {
            referenciaMovimientoService.EliminarRef(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
