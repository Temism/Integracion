package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRep extends JpaRepository<Marca,Long> {
}
