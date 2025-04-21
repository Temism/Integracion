package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovimientoInventarioRep extends JpaRepository<MovimientoInventario,Long> {

}

