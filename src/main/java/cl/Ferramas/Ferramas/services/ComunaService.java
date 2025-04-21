package cl.Ferramas.Ferramas.services;



import cl.Ferramas.Ferramas.entity.Comuna;

import cl.Ferramas.Ferramas.repository.ComunRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComunaService {

    @Autowired
    private ComunRep comunRep;

    public List<Comuna> listarComunas(){
        return comunRep.findAll();
    }

    public Optional<Comuna> buscarPorId(Long comunaId){
        return comunRep.findById(comunaId);
    }

    public Comuna guardarComuna(Comuna comuna) {
        return comunRep.save(comuna);
    }

    public void eliminarComuna(Long comunaId) {
        comunRep.deleteById(comunaId);
    }
}
