package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.EstadoPago;
import cl.Ferramas.Ferramas.entity.Pago;
import cl.Ferramas.Ferramas.repository.EstadoPagoRep;
import cl.Ferramas.Ferramas.repository.PagoRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRep pagoRep;

    @Autowired
    private EstadoPagoRep estadoPagoRep;

    public List<Pago> ListarPagos(){
        return pagoRep.findAll();
    }

    public Optional<Pago> BuscarPagoPorId(Long pagoId){
        return pagoRep.findById(pagoId);
    }

    public Pago guardarPago(Pago pago) {
        return pagoRep.save(pago);
    }

    public void EliminarPago(Long pagoId) {
        pagoRep.deleteById(pagoId);
    }

    @Transactional
    public boolean actualizarEstadoPago(Long pagoId, Long estadoId) {
        Optional<Pago> pagoOptional = pagoRep.findById(pagoId);
        if (pagoOptional.isPresent()) {
            Pago pago = pagoOptional.get();

            EstadoPago nuevoEstado = estadoPagoRep.findById(estadoId)
                .orElseThrow(() -> new RuntimeException("Estado de pago no encontrado"));

            pago.setEstado(nuevoEstado);
            pagoRep.save(pago);
            return true;
        }
        return false;
    }
}
