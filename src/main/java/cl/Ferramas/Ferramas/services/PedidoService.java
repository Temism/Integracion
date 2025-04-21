package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.*;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRep pedidoRep;

    public List<Pedido> ListarPedidos(){
        return pedidoRep.findAll();
    }

    public Optional<Pedido> BuscarPedidoPorId(Long pedidoId){
        return pedidoRep.findById(pedidoId);
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRep.save(pedido);
    }

    public void EliminarPedido(Long pedidoId) {
        pedidoRep.deleteById(pedidoId);
    }
}

