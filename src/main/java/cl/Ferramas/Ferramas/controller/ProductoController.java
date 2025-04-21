package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.Producto;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAll() {
        return productoService.BuscarTodosProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        return productoService.BuscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto create(@RequestBody Producto prod) {
        return productoService.GuardarProducto(prod);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (productoService.BuscarPorId(id).isPresent()) {
            productoService.EliminarProducto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
