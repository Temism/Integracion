package cl.Ferramas.Ferramas.services;



import cl.Ferramas.Ferramas.entity.Comuna;
import cl.Ferramas.Ferramas.entity.DetallePedido;
import cl.Ferramas.Ferramas.repository.DetallePedidoRep;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DetallePedidoService {


    @Autowired
    private DetallePedidoRep detallePedidoRep;

    public Optional<DetallePedido> buscarPorId(Long detallePedidoId){
        return detallePedidoRep.findById(detallePedidoId);
    }
    public List<DetallePedido> buscarTodosLosDetalles(){
        return detallePedidoRep.findAll();
    }

    public void eliminarDetallePedido(Long detallePedidoId) {
        detallePedidoRep.deleteById(detallePedidoId);
    }

    public DetallePedido guardarDetalle(DetallePedido detallePedido) {
        return detallePedidoRep.save(detallePedido);
    }

}