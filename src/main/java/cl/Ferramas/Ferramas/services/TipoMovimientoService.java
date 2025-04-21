package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Comuna;
import cl.Ferramas.Ferramas.entity.TipoMovimiento;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.repository.ComunRep;
import cl.Ferramas.Ferramas.repository.TipoMovimientoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMovimientoService {

    @Autowired
    private TipoMovimientoRep tipomov;

    @Autowired
    private ComunRep comunRep;

    public List<TipoMovimiento> ListarMovimientos(){
        return tipomov.findAll();
    }

    public Optional<TipoMovimiento> buscarPorId(Long tipoMovimientoId){
        return tipomov.findById(tipoMovimientoId);
    }

    public TipoMovimiento guardarTipoMov(TipoMovimiento tmov) {
        return tipomov.save(tmov);
    }

    public void eliminarTipoMov(Long tipoMovimientoId) {
        tipomov.deleteById(tipoMovimientoId);
    }

}
