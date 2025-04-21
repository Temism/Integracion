package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Region;
import cl.Ferramas.Ferramas.entity.UbicacionBodega;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.repository.RegionRep;
import cl.Ferramas.Ferramas.repository.UbicacionBodegaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionBodegaService {


    @Autowired
    private UbicacionBodegaRep ubica;



    public List<UbicacionBodega> ListarUbicaciones(){
        return ubica.findAll();
    }

    public Optional<UbicacionBodega> BuscarUbicacionPorId(Long ubicacionId){
        return ubica.findById(ubicacionId);
    }

    public UbicacionBodega guardarUbicacion(UbicacionBodega ubic) {
        return ubica.save(ubic);
    }

    public void EliminarUbicacion(Long ubicacionId) {
        ubica.deleteById(ubicacionId);
    }
}
