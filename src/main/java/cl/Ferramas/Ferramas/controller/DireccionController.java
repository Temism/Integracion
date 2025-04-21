package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.DetallePedido;
import cl.Ferramas.Ferramas.entity.Direccion;
import cl.Ferramas.Ferramas.services.DetallePedidoService;
import cl.Ferramas.Ferramas.services.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @GetMapping
    public List<Direccion> getAll() {
        return direccionService.listarDirecciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getById(@PathVariable Long id) {
        return direccionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Direccion create(@RequestBody Direccion direccion) {
        return direccionService.guardarDireccion(direccion);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (direccionService.buscarPorId(id).isPresent()) {
            direccionService.ELiminarDireccion(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
