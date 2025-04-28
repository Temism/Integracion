package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.entity.Ciudad;

import cl.Ferramas.Ferramas.services.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @GetMapping
    public List<Ciudad> getAll() {
        return ciudadService.listarCiudades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getById(@PathVariable Long id) {
        return ciudadService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ciudad create(@RequestBody Ciudad ciudad) {
        return ciudadService.guardarCiudad(ciudad);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (ciudadService.buscarPorId(id).isPresent()) {
            ciudadService.eliminarCiudadPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
