package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.UbicacionBodega;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UbicacionBodegaRep extends JpaRepository<UbicacionBodega,Long> {

}
