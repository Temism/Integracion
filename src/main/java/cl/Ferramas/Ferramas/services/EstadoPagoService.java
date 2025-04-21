package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.EstadoPago;
import cl.Ferramas.Ferramas.entity.EstadoPedido;
import cl.Ferramas.Ferramas.repository.EstadoPagoRep;
import cl.Ferramas.Ferramas.repository.EstadoPedidoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoPagoService {

    @Autowired
    private EstadoPagoRep estadoPagoRep;

    public List<EstadoPago> listarEstadoPagos(){
        return estadoPagoRep.findAll();
    }

    public Optional<EstadoPago> buscarPorId(Long estadoPagoId){
        return estadoPagoRep.findById(estadoPagoId);
    }

    public EstadoPago guardarEstadoPago(EstadoPago estadoPedido) {
        return estadoPagoRep.save(estadoPedido);
    }

    public void EliminarEstadoPago(Long estadoPagoId) {
        estadoPagoRep.deleteById(estadoPagoId);
    }
}
