package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.TipoPago;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipoPagoRep extends JpaRepository<TipoPago,Long> {

}
