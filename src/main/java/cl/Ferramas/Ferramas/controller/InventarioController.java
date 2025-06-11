package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.dto.InventarioDTO;
import cl.Ferramas.Ferramas.dto.PedidoDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;
import cl.Ferramas.Ferramas.entity.Inventario;

import cl.Ferramas.Ferramas.services.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<InventarioDTO> getAll() {
        return inventarioService.listarInventario();
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<InventarioDTO>> obtenerInventariosPorSucursal(@PathVariable Long sucursalId) {
        List<InventarioDTO> inventarios = inventarioService.obtenerInventariosPorSucursal(sucursalId);

        if (inventarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);
    }


    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> Inventarioporid(@PathVariable Long id) {
        InventarioDTO inventarioDTO = inventarioService.buscarInventarioporId(id);

        if (inventarioDTO != null) {
            return ResponseEntity.ok(inventarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<InventarioDTO> registrarInventario(@Valid @RequestBody InventarioDTO inventarioDTO) {
        InventarioDTO inventario = inventarioService.guardarInventario(inventarioDTO);
        return new ResponseEntity<>(inventario, HttpStatus.CREATED);
    }

    @PatchMapping("/actualizarInventario/{id}")
    public ResponseEntity<InventarioDTO> actualizarInventario(@PathVariable Long id, @RequestBody InventarioDTO inventarioDTO){

        InventarioDTO inventario = inventarioService.actualizarInventario(id, inventarioDTO);

        if (inventario != null){
            return ResponseEntity.ok(inventario);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

}
