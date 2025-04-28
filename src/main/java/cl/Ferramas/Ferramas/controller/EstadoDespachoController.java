package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.Despacho;
import cl.Ferramas.Ferramas.entity.EstadoDespacho;
import cl.Ferramas.Ferramas.services.DespachoService;
import cl.Ferramas.Ferramas.services.EstadoDespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadodespacho")
public class EstadoDespachoController {

    @Autowired
    private EstadoDespachoService estadoDespachoService;

    @GetMapping
    public List<EstadoDespacho> getAll() {
        return estadoDespachoService.ListarEstadosDespacho();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDespacho> getById(@PathVariable Long id) {
        return estadoDespachoService.BuscaEstadoDespachoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EstadoDespacho create(@RequestBody EstadoDespacho estadoDespacho) {
        return estadoDespachoService.GuardarEstadoDespacho(estadoDespacho);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (estadoDespachoService.BuscaEstadoDespachoPorId(id).isPresent()) {
            estadoDespachoService.BorrarEstadoDespacho(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
