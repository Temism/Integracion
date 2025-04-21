package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.ReferenciaMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferenciaMovimientoRep extends JpaRepository<ReferenciaMovimiento,Long> {



}
