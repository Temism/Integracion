package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.entity.EstadoDespacho;

import cl.Ferramas.Ferramas.repository.EstadoDespachoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoDespachoService {
    @Autowired
    private EstadoDespachoRep estadoDespachoRep;

    public List<EstadoDespacho> ListarEstadosDespacho() {
        return estadoDespachoRep.findAll();
    }

    public Optional<EstadoDespacho> BuscaEstadoDespachoPorId(Long estadoDespachoId) {
        return estadoDespachoRep.findById(estadoDespachoId);
    }

    public EstadoDespacho GuardarEstadoDespacho(EstadoDespacho estadoDespacho) {
        return estadoDespachoRep.save(estadoDespacho);
    }

    public void BorrarEstadoDespacho(Long estadoDespachoId) {
        estadoDespachoRep.deleteById(estadoDespachoId);
    }

}
