package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoMovimientoRep extends JpaRepository<TipoMovimiento,Long> {



}
