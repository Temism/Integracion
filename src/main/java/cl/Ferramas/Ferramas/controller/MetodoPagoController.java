package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.entity.MetodoPago;

import cl.Ferramas.Ferramas.services.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metodopago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public List<MetodoPago> getAll() {
        return metodoPagoService.BuscarMetodosPago();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> getById(@PathVariable Long id) {
        return metodoPagoService.BuscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MetodoPago create(@RequestBody MetodoPago metodo) {
        return metodoPagoService.guardarMetodoPago(metodo);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (metodoPagoService.BuscarPorId(id).isPresent()) {
            metodoPagoService.EliminarMetodoPago(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
