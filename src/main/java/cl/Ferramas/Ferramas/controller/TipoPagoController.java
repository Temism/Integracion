package cl.Ferramas.Ferramas.controller;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.TipoPago;
import cl.Ferramas.Ferramas.services.MetodoPagoService;
import cl.Ferramas.Ferramas.services.TipoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipopago")
public class TipoPagoController {

    @Autowired
    private TipoPagoService tipoPagoService;

    @GetMapping
    public List<TipoPago> getAll() {
        return tipoPagoService.BuscarTodoTipoPago();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPago> getById(@PathVariable Long id) {
        return tipoPagoService.BuscarTipoPagoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoPago create(@RequestBody TipoPago tipo) {
        return tipoPagoService.GuardarTipoPago(tipo);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tipoPagoService.BuscarTipoPagoPorId(id).isPresent()) {
            tipoPagoService.BorrarTipoPagoPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
