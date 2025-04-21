package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.TipoReferencia;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.repository.TipoReferenciaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoReferenciaService {

    @Autowired
    private TipoReferenciaRep tipoReferenciaRepository;

    public List<TipoReferencia> Buscartodaslasref() {
        return tipoReferenciaRepository.findAll();
    }

    public Optional<TipoReferencia> BuscarRefPorId(Long id) {
        return tipoReferenciaRepository.findById(id);
    }

  //  public TipoReferencia findByNombre(String nombre) {
  //      return tipoReferenciaRepository.findByNombre(nombre);
  //  }

    public TipoReferencia GuardarRef(TipoReferencia tipoReferencia) {
        return tipoReferenciaRepository.save(tipoReferencia);
    }

    public void BorrarRedId(Long id) {
        tipoReferenciaRepository.deleteById(id);
    }
}
