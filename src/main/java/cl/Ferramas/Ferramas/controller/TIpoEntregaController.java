package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.TipoEntrega;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.TipoEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoentrega")
public class TIpoEntregaController {

    @Autowired
    private TipoEntregaService tipoEntregaService;

    @GetMapping
    public List<TipoEntrega> getAll() {
        return tipoEntregaService.ListarTiposEntrega();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEntrega> getById(@PathVariable Long id) {
        return tipoEntregaService.BuscarEntegaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoEntrega create(@RequestBody TipoEntrega tipoEntrega) {
        return tipoEntregaService.GuardarTipoEntrega(tipoEntrega);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tipoEntregaService.BuscarEntegaPorId(id).isPresent()) {
            tipoEntregaService.EliminarTipoEntrega(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
