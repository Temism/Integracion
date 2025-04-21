package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadRep extends JpaRepository<Ciudad,Long> {


}
