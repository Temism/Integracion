package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.dto.ProductoDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;


import cl.Ferramas.Ferramas.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<RegistroProductoDTO> registrarproducto(@Valid @RequestBody RegistroProductoDTO registroDTO) {
        RegistroProductoDTO productoregistrado = productoService.registrarproducto(registroDTO);
        return new ResponseEntity<>(productoregistrado, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> productoporId(@PathVariable Long id) {
        ProductoDTO productoDTO = productoService.buscarprodporId(id);

        if (productoDTO != null) {
            return ResponseEntity.ok(productoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<ProductoDTO> getAll() {
        return productoService.listarProductos();
    }

    @PatchMapping("/actualizarproducto/{id}")
    public ResponseEntity<RegistroProductoDTO> actualizarproducto(@PathVariable Long id, @RequestBody RegistroProductoDTO productoDTO){

        RegistroProductoDTO producto = productoService.actualizarProducto(id, productoDTO);

        if (producto != null){
            return ResponseEntity.ok(producto);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

}
