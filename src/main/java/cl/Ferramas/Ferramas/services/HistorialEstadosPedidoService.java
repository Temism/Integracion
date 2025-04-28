package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.entity.HistorialEstadoPedido;
import cl.Ferramas.Ferramas.repository.HistorialEstadoPedidoRep;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class HistorialEstadosPedidoService {

    @Autowired
    private HistorialEstadoPedidoRep historialEstadoPedidoRep;

    public List<HistorialEstadoPedido> listaHistorialEstadoPedido(){
        return historialEstadoPedidoRep.findAll();
    }

    public Optional<HistorialEstadoPedido> buscarPorId(Long historiaEstadoPedidolId){
        return historialEstadoPedidoRep.findById(historiaEstadoPedidolId);
    }

    public HistorialEstadoPedido guardarHistorial(HistorialEstadoPedido historialEstadoPedido) {
        return historialEstadoPedidoRep.save(historialEstadoPedido);
    }

    public void ELiminarHistorial(Long historiaEstadoPedidolId) {
        historialEstadoPedidoRep.deleteById(historiaEstadoPedidolId);
    }


}
