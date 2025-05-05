package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.dto.ProductoDTO;
import cl.Ferramas.Ferramas.dto.RegistroProductoDTO;


import cl.Ferramas.Ferramas.dto.UsuarioDTO;
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

    @PostMapping("/registrarproducto")
    public ResponseEntity<RegistroProductoDTO> registrarproducto(@Valid @RequestBody RegistroProductoDTO registroDTO) {
        RegistroProductoDTO productoregistrado = productoService.registrarproducto(registroDTO);
        return new ResponseEntity<>(productoregistrado, HttpStatus.CREATED);
    }

    @GetMapping("/listarproductos")
    public List<ProductoDTO> getAll() {
        return productoService.listarProductos();
    }

}
