package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.dto.ComunaDTO;
import cl.Ferramas.Ferramas.dto.ProductoDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;
import cl.Ferramas.Ferramas.entity.Comuna;
import cl.Ferramas.Ferramas.services.ComunaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comuna")
public class ComunaController {


    @Autowired
    private ComunaService comunaService;

    @GetMapping("/listacomunas")
    public List<ComunaDTO> getAll() {
        return comunaService.listaComunas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> comunaporId(@PathVariable Long id) {
        ComunaDTO comunaDTO = comunaService.buscarComunaporId(id);

        if (comunaDTO != null) {
            return ResponseEntity.ok(comunaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registrarcomuna")
    public ResponseEntity<ComunaDTO> registrarcomuna(@Valid @RequestBody ComunaDTO comunaDTO) {
        ComunaDTO comuna = comunaService.guardarComuna(comunaDTO);
        return new ResponseEntity<>(comuna, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ComunaDTO comuna = comunaService.buscarComunaporId(id);

        if (comuna != null) {
            comunaService.eliminarComuna(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
