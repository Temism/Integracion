package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.TipoPago;

import cl.Ferramas.Ferramas.repository.TipoPagoRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipoPagoService {

    private final TipoPagoRep tipoPagoRepository;

    @Autowired
    public TipoPagoService(TipoPagoRep tipoPagoRepository) {
        this.tipoPagoRepository = tipoPagoRepository;
    }

    public List<TipoPago> BuscarTodoTipoPago() {
        return tipoPagoRepository.findAll();
    }

    public Optional<TipoPago> BuscarTipoPagoPorId(Long tipoPagoId) {
        return tipoPagoRepository.findById(tipoPagoId);
    }

    public TipoPago GuardarTipoPago(TipoPago tipoPago) {
        return tipoPagoRepository.save(tipoPago);
    }

    public void BorrarTipoPagoPorId(Long tipoPagoId) {
        tipoPagoRepository.deleteById(tipoPagoId);
    }

   /* public List<TipoPago> findByNombreContaining(String nombre) {
        return tipoPagoRepository.findByNombreContaining(nombre);
    }

    public boolean existsById(Long tipoPagoId) {
        return tipoPagoRepository.existsById(tipoPagoId);
    }*/
}