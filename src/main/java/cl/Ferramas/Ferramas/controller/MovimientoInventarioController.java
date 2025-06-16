package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.dto.MovimientoInventarioDTO;

import cl.Ferramas.Ferramas.services.MovimientoInventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientoinventario")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;

    @GetMapping
    public List<MovimientoInventarioDTO> getAll() {
        return movimientoInventarioService.BuscarTodosLosMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventarioDTO> getById(@PathVariable Long id) {
        MovimientoInventarioDTO movimientoInventarioDTO = movimientoInventarioService.BuscarMovimientoPorId(id);

        if (movimientoInventarioDTO != null) {
            return ResponseEntity.ok(movimientoInventarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MovimientoInventarioDTO> create(@Valid @RequestBody MovimientoInventarioDTO movimientoInventarioDTO) {
        MovimientoInventarioDTO movimientoregistrado = movimientoInventarioService.guardarMovimientoInventario(movimientoInventarioDTO);
        return new ResponseEntity<>(movimientoregistrado, HttpStatus.CREATED);
    }





}
