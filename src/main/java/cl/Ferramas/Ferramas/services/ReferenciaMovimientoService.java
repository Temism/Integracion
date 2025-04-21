package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Producto;
import cl.Ferramas.Ferramas.entity.ReferenciaMovimiento;

import cl.Ferramas.Ferramas.repository.ProductoRep;
import cl.Ferramas.Ferramas.repository.ReferenciaMovimientoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ReferenciaMovimientoService {

    @Autowired
    private ReferenciaMovimientoRep referenciaMovimientoRep;

    public List<ReferenciaMovimiento> ListarReferenciasMov(){
        return referenciaMovimientoRep.findAll();
    }

    public Optional<ReferenciaMovimiento> BuscarRefMovPorId(Long referenciaId){
        return referenciaMovimientoRep.findById(referenciaId);
    }

    public ReferenciaMovimiento GuardarRef(ReferenciaMovimiento ref) {
        return referenciaMovimientoRep.save(ref);
    }

    public void EliminarRef(Long referenciaId) {
        referenciaMovimientoRep.deleteById(referenciaId);
    }
}
