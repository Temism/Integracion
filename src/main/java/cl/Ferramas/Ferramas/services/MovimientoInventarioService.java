package cl.Ferramas.Ferramas.services;


import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.entity.MovimientoInventario;

import cl.Ferramas.Ferramas.repository.MovimientoInventarioRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MovimientoInventarioService {

    @Autowired
    private MovimientoInventarioRep movimientoInventarioRepository;

    public List<MovimientoInventario> BuscarTodosLosMovimientos() {
        return movimientoInventarioRepository.findAll();
    }

    public Optional<MovimientoInventario> BuscarMovimientoPorId(Long movimientoId) {
        return movimientoInventarioRepository.findById(movimientoId);
    }

    public MovimientoInventario guardarMovimientoInventario(MovimientoInventario movimientoInventario) {
        return movimientoInventarioRepository.save(movimientoInventario);
    }

    public void EliminarMovimientoInventario(Long movimientoId) {
        movimientoInventarioRepository.deleteById(movimientoId);
    }



}