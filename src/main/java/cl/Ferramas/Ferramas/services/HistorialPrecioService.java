package cl.Ferramas.Ferramas.services;



import cl.Ferramas.Ferramas.entity.HistorialPrecio;

import cl.Ferramas.Ferramas.repository.HistorialPrecioRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistorialPrecioService {

    @Autowired
    private HistorialPrecioRep historialPrecioRep;

    public List<HistorialPrecio> listaHistorialPrecio(){
        return historialPrecioRep.findAll();
    }

    public Optional<HistorialPrecio> buscarPorIdHP(Long historialPrecioId){
        return historialPrecioRep.findById(historialPrecioId);
    }

    public HistorialPrecio guardarHistorialPrecio(HistorialPrecio historialPrecio) {
        return historialPrecioRep.save(historialPrecio);
    }

    public void ELiminarHistorialPrecio(Long historialPrecioId) {
        historialPrecioRep.deleteById(historialPrecioId);
    }
}
