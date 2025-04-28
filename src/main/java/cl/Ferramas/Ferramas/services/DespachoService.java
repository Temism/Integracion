package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.entity.Despacho;
import cl.Ferramas.Ferramas.entity.DetallePedido;
import cl.Ferramas.Ferramas.repository.DespachoRep;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DespachoService {

    @Autowired
    private DespachoRep despachoRep;

    public Optional<Despacho> buscarPorId(Long despachoId){
        return despachoRep.findById(despachoId);
    }
    public List<Despacho> BuscarTodosLosDespachos(){
        return despachoRep.findAll();
    }

    public void EliminarDespacho(Long despachoId) {
        despachoRep.deleteById(despachoId);
    }

    public Despacho GuardarDespacho(Despacho despacho) {
        return despachoRep.save(despacho);
    }
}
