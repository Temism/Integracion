package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Sucursal;
import cl.Ferramas.Ferramas.entity.TipoEntrega;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.repository.TipoEntregaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEntregaService {

    private final TipoEntregaRep tipoEntregaRepository;

    @Autowired
    public TipoEntregaService(TipoEntregaRep tipoEntregaRepository) {
        this.tipoEntregaRepository = tipoEntregaRepository;
    }

    public List<TipoEntrega> ListarTiposEntrega() {
        return tipoEntregaRepository.findAll();
    }

    public Optional<TipoEntrega> BuscarEntegaPorId(Long tipoEntregaId) {
        return tipoEntregaRepository.findById(tipoEntregaId);

    }

    public TipoEntrega GuardarTipoEntrega(TipoEntrega tipoEntrega) {
        return tipoEntregaRepository.save(tipoEntrega);
    }

    public void EliminarTipoEntrega(Long tipoEntregaId) {
        tipoEntregaRepository.deleteById(tipoEntregaId);
    }

    /*public TipoEntrega findByNombre(String nombre) {
        return tipoEntregaRepository.findByNombre(nombre)
                .orElseThrow(() -> new ExceptionClasses("Tipo de entrega no encontrado con nombre: " + nombre));
    }

    public TipoEntrega save(TipoEntrega tipoEntrega) {
        return tipoEntregaRepository.save(tipoEntrega);
    }*/


}
