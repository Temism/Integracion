package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.MetodoPago;
import cl.Ferramas.Ferramas.repository.MetodoPagoRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MetodoPagoService {

    private final MetodoPagoRep metodoPagoRepository;

    @Autowired
    public MetodoPagoService(MetodoPagoRep metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    public List<MetodoPago> BuscarMetodosPago() {
        return metodoPagoRepository.findAll();
    }

    public Optional<MetodoPago> BuscarPorId(Long metodoId) {
        return metodoPagoRepository.findById(metodoId);
    }

    public MetodoPago guardarMetodoPago(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    public void EliminarMetodoPago(Long metodoId) {
        metodoPagoRepository.deleteById(metodoId);
    }

}
