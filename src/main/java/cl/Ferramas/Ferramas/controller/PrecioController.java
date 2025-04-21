package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.Precio;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.PrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/precio")
public class PrecioController {

    @Autowired
    private PrecioService precioService;

    @GetMapping
    public List<Precio> getAll() {
        return precioService.ListarPrecios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Precio> getById(@PathVariable Long id) {
        return precioService.BuscarPrecioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Precio create(@RequestBody Precio precio) {
        return precioService.GuardarPrecio(precio);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (precioService.BuscarPrecioPorId(id).isPresent()) {
            precioService.EliminarPrecio(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
