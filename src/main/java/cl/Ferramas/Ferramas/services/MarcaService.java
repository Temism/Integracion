package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Inventario;
import cl.Ferramas.Ferramas.entity.Marca;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.repository.InventarioRep;
import cl.Ferramas.Ferramas.repository.MarcaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRep marcaRep;

    public List<Marca> listarMarca(){
        return marcaRep.findAll();
    }

    public Optional<Marca> buscarPorId(Long marcaId){
        return marcaRep.findById(marcaId);
    }

    public Marca guardarMarca(Marca marca) {
        return marcaRep.save(marca);
    }

    public void EliminarMarca(Long marcaId) {
        marcaRep.deleteById(marcaId);
    }


}
