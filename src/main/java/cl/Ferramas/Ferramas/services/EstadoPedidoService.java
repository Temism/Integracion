package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.EstadoPedido;

import cl.Ferramas.Ferramas.repository.EstadoPedidoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoPedidoService {

    @Autowired
    private EstadoPedidoRep estadoPedidoRep;

    public List<EstadoPedido> listarEstadoPedidos(){
        return estadoPedidoRep.findAll();
    }

    public Optional<EstadoPedido> buscarPorId(Long estadoPedidoId){
        return estadoPedidoRep.findById(estadoPedidoId);
    }

    public EstadoPedido guardarEstadoPedido(EstadoPedido estadoPedido) {
        return estadoPedidoRep.save(estadoPedido);
    }

    public void EliminarEstadoPedido(Long estadoPedidoId) {
        estadoPedidoRep.deleteById(estadoPedidoId);
    }

}
