package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.dto.ClienteDTO;
import cl.Ferramas.Ferramas.dto.DespachoDTO;
import cl.Ferramas.Ferramas.dto.InventarioDTO;
import cl.Ferramas.Ferramas.dto.RegistroUsuarioDTO;
import cl.Ferramas.Ferramas.entity.Despacho;

import cl.Ferramas.Ferramas.services.DespachoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despacho")
public class DespachoController {
    @Autowired
    private DespachoService despachoService;

    @GetMapping
    public List<DespachoDTO> getAll() {
        return despachoService.listardespachos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespachoDTO> despachoporid(@PathVariable Long id) {
        DespachoDTO despachoDTO = despachoService.buscardespachoporId(id);
        if (despachoDTO != null) {
            return ResponseEntity.ok(despachoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DespachoDTO> registrarDespacho(@Valid @RequestBody DespachoDTO despachoDTO) {
        DespachoDTO despacho = despachoService.registrarproducto(despachoDTO);
        return new ResponseEntity<>(despacho, HttpStatus.CREATED);
    }

    @PatchMapping("/actualizardespacho/{id}")
    public ResponseEntity <DespachoDTO> actualizardespacho(@PathVariable Long id, @RequestBody DespachoDTO despachoDTO){

        DespachoDTO despacho = despachoService.actualizardespacho(id,despachoDTO);

        if (despacho != null){
            return ResponseEntity.ok(despacho);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


}
