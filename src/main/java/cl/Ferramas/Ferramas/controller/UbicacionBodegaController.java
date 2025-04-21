package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.UbicacionBodega;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.UbicacionBodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicacionbodega")
public class UbicacionBodegaController {

    @Autowired
    private UbicacionBodegaService ubicacionBodegaService;

    @GetMapping
    public List<UbicacionBodega> getAll() {
        return ubicacionBodegaService.ListarUbicaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionBodega> getById(@PathVariable Long id) {
        return ubicacionBodegaService.BuscarUbicacionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UbicacionBodega create(@RequestBody UbicacionBodega ubic) {
        return ubicacionBodegaService.guardarUbicacion(ubic);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ubicacionBodegaService.BuscarUbicacionPorId(id).isPresent()) {
            ubicacionBodegaService.EliminarUbicacion(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
