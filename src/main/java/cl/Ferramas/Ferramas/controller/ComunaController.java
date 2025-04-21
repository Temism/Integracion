package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.entity.Ciudad;
import cl.Ferramas.Ferramas.entity.Comuna;
import cl.Ferramas.Ferramas.services.CiudadService;
import cl.Ferramas.Ferramas.services.ComunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comuna")
public class ComunaController {


    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public List<Comuna> getAll() {
        return comunaService.listarComunas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> getById(@PathVariable Long id) {
        return comunaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Comuna create(@RequestBody Comuna comuna) {
        return comunaService.guardarComuna(comuna);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (comunaService.buscarPorId(id).isPresent()) {
            comunaService.eliminarComuna(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
