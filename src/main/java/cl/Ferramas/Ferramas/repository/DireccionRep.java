package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DireccionRep extends JpaRepository<Direccion,Long> {

}
