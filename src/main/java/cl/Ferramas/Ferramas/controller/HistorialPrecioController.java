package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.EstadoDespacho;
import cl.Ferramas.Ferramas.entity.HistorialPrecio;
import cl.Ferramas.Ferramas.services.EstadoDespachoService;
import cl.Ferramas.Ferramas.services.HistorialEstadosPedidoService;
import cl.Ferramas.Ferramas.services.HistorialPrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historialprecio")
public class HistorialPrecioController {

    @Autowired
    private HistorialPrecioService historialPrecioService;

    @GetMapping
    public List<HistorialPrecio> getAll() {
        return historialPrecioService.listaHistorialPrecio();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialPrecio> getById(@PathVariable Long id) {
        return historialPrecioService.buscarPorIdHP(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HistorialPrecio create(@RequestBody HistorialPrecio historialPrecio) {
        return historialPrecioService.guardarHistorialPrecio(historialPrecio);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (historialPrecioService.buscarPorIdHP(id).isPresent()) {
            historialPrecioService.ELiminarHistorialPrecio(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
