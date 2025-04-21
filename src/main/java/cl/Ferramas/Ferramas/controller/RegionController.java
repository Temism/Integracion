package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.Region;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public List<Region> getAll() {
        return regionService.ListarRegiones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getById(@PathVariable Long id) {
        return regionService.BuscarRegionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Region create(@RequestBody Region region) {
        return regionService.guardarRegion(region);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (regionService.BuscarRegionPorId(id).isPresent()) {
            regionService.EliminarRegion(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
