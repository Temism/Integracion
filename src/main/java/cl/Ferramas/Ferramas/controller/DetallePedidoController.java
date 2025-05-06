package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.dto.DetallePedidoDTO;


import cl.Ferramas.Ferramas.services.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallepedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;


    @GetMapping("/listaDetalles")
    public List<DetallePedidoDTO> getAll() {
        return detallePedidoService.listarDetallesPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> detallepedidoporid(@PathVariable Long id) {
        DetallePedidoDTO detallePedidoDTO = detallePedidoService.buscardetallepedidoporId(id);
        if (detallePedidoDTO != null) {
            return ResponseEntity.ok(detallePedidoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registrardetalle")
    public ResponseEntity<DetallePedidoDTO> registrarDetallePedido(@Valid @RequestBody DetallePedidoDTO detallePedidoDTO) {
        DetallePedidoDTO detalle = detallePedidoService.registrarproducto(detallePedidoDTO);
        return new ResponseEntity<>(detalle, HttpStatus.CREATED);
    }




}
