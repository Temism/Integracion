package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.*;

import cl.Ferramas.Ferramas.repository.DireccionRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {


    @Autowired
    private DireccionRep direccionRep;

    public List<Direccion> listarDirecciones(){
        return direccionRep.findAll();
    }

    public Optional<Direccion> buscarPorId(Long direccionId){
        return direccionRep.findById(direccionId);
    }

    public Direccion guardarDireccion(Direccion direccion) {
        return direccionRep.save(direccion);
    }

    public void ELiminarDireccion(Long direccionId) {
        direccionRep.deleteById(direccionId);
    }


}
