package cl.Ferramas.Ferramas.services;



import cl.Ferramas.Ferramas.entity.Pago;
import cl.Ferramas.Ferramas.repository.PagoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRep pagoRep;

    public List<Pago> ListarPagos(){
        return pagoRep.findAll();
    }

    public Optional<Pago> BuscarPagoPorId(Long pagoId){
        return pagoRep.findById(pagoId);
    }

    public Pago guardarPago(Pago pago) {
        return pagoRep.save(pago);
    }

    public void EliminarPago(Long pagoId) {
        pagoRep.deleteById(pagoId);
    }
}