package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.entity.Ciudad;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;

import cl.Ferramas.Ferramas.repository.CiudadRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService {

    @Autowired
    private CiudadRep ciudadRep;

    public List<Ciudad> listarCiudades(){
        return ciudadRep.findAll();
    }

    public Optional<Ciudad> buscarPorId(Long ciudadId){
        return ciudadRep.findById(ciudadId);
    }

    public Ciudad guardarCiudad(Ciudad ciudad) {
        return ciudadRep.save(ciudad);
    }

    public void eliminarCiudadPorId(Long categoriaId) {
        ciudadRep.deleteById(categoriaId);
    }
}
