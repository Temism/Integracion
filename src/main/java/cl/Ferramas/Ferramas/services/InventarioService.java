package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.entity.EstadoPedido;
import cl.Ferramas.Ferramas.entity.Inventario;
import cl.Ferramas.Ferramas.repository.InventarioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class InventarioService {

    @Autowired
    private InventarioRep inventarioRep;

    public List<Inventario> listarInventario(){
        return inventarioRep.findAll();
    }

    public Optional<Inventario> buscarPorId(Long inventarioId){
        return inventarioRep.findById(inventarioId);
    }

    public Inventario guardarInventario(Inventario inventario) {
        return inventarioRep.save(inventario);
    }

    public void EliminarInventario(Long inventarioId) {
        inventarioRep.deleteById(inventarioId);
    }


}