package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.entity.Despacho;

import cl.Ferramas.Ferramas.services.DespachoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despacho")
public class DespachoController {
    @Autowired
    private DespachoService despachoRep;

    @GetMapping
    public List<Despacho> getAll() {
        return despachoRep.BuscarTodosLosDespachos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despacho> getById(@PathVariable Long id) {
        return despachoRep.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Despacho create(@RequestBody Despacho despacho) {
        return despachoRep.GuardarDespacho(despacho);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (despachoRep.buscarPorId(id).isPresent()) {
            despachoRep.EliminarDespacho(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
