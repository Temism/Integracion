package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.ReferenciaMovimiento;
import cl.Ferramas.Ferramas.entity.TipoReferencia;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.ReferenciaMovimientoService;
import cl.Ferramas.Ferramas.services.TipoReferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiporef")
public class TipoReferenciaController {

    @Autowired
    private TipoReferenciaService tipoReferencia;

    @GetMapping
    public List<TipoReferencia> getAll() {
        return tipoReferencia.Buscartodaslasref();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoReferencia> getById(@PathVariable Long id) {
        return tipoReferencia.BuscarRefPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoReferencia create(@RequestBody TipoReferencia tiporef) {
        return tipoReferencia.GuardarRef(tiporef);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tipoReferencia.BuscarRefPorId(id).isPresent()) {
            tipoReferencia.BorrarRedId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
