package cl.Ferramas.Ferramas.controller;



import cl.Ferramas.Ferramas.dto.PedidoDTO;


import cl.Ferramas.Ferramas.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/listarpedidos")
    public List<PedidoDTO> getAll() {
        return pedidoService.listarpedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> pedidoporId(@PathVariable Long id) {
        PedidoDTO pedidoDTO = pedidoService.buscarpedidoporId(id);
        if (pedidoDTO != null) {
            return ResponseEntity.ok(pedidoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/registrarpedido")
    public ResponseEntity<PedidoDTO> registrarpedido(@Valid @RequestBody PedidoDTO PedidoDTO) {
        PedidoDTO pedido = pedidoService.registrarPedido(PedidoDTO);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }




}
