package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.TipoMovimientoInventario;
import cl.Ferramas.Ferramas.repository.ComunRep;
import cl.Ferramas.Ferramas.repository.TipoMovimientoInventarioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMovimientoInventarioService {

    @Autowired
    private TipoMovimientoInventarioRep tipomov;

    @Autowired
    private ComunRep comunRep;

    public List<TipoMovimientoInventario> ListarMovimientos(){
        return tipomov.findAll();
    }

    public Optional<TipoMovimientoInventario> buscarPorId(Long tipoMovimientoId){
        return tipomov.findById(tipoMovimientoId);
    }

    public TipoMovimientoInventario guardarTipoMov(TipoMovimientoInventario tmov) {
        return tipomov.save(tmov);
    }

    public void eliminarTipoMov(Long tipoMovimientoId) {
        tipomov.deleteById(tipoMovimientoId);
    }

}
