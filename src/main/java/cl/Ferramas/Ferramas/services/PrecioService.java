package cl.Ferramas.Ferramas.services;

import cl.Ferramas.Ferramas.entity.Pago;
import cl.Ferramas.Ferramas.entity.Precio;
import cl.Ferramas.Ferramas.exception.ExceptionClasses;
import cl.Ferramas.Ferramas.repository.PagoRep;
import cl.Ferramas.Ferramas.repository.PrecioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrecioService {

    @Autowired
    private PrecioRep precioRep;

    public List<Precio> ListarPrecios(){
        return precioRep.findAll();
    }

    public Optional<Precio> BuscarPrecioPorId(Long precioId){
        return precioRep.findById(precioId);
    }

    public Precio GuardarPrecio(Precio precio) {
        return precioRep.save(precio);
    }

    public void EliminarPrecio(Long precioId) {
        precioRep.deleteById(precioId);
    }
}