package cl.Ferramas.Ferramas.controller;

import cl.Ferramas.Ferramas.dto.ComprobanteDTO;
import cl.Ferramas.Ferramas.dto.PedidoDTO;
import cl.Ferramas.Ferramas.dto.CambioEstadoDTO; // ✅ Nuevo DTO
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

    @GetMapping
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

    @PostMapping
    public ResponseEntity<PedidoDTO> registrarpedido(@Valid @RequestBody PedidoDTO PedidoDTO) {
        PedidoDTO pedido = pedidoService.registrarPedido(PedidoDTO);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @PostMapping("/comprobante/guardar")
    public ResponseEntity<String> guardarComprobante(@RequestBody ComprobanteDTO dto) {
        pedidoService.actualizarComprobante(dto.getPedidoId(), dto.getUrlComprobante());
        return ResponseEntity.ok("Comprobante guardado con éxito");
    }

    // ✅ NUEVO: Cambiar estado de pedido por vendedor
    @PatchMapping("/cambiarEstado/{id}")
    public ResponseEntity<String> cambiarEstadoPedido(
            @PathVariable Long id,
            @RequestBody CambioEstadoDTO dto
    ) {
        boolean actualizado = pedidoService.cambiarEstadoPedido(id, dto);
        if (actualizado) {
            return ResponseEntity.ok("Estado del pedido actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo actualizar el estado del pedido.");
        }
    }
}
