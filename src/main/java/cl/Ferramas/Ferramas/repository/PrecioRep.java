package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Precio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrecioRep extends JpaRepository<Precio,Long> {

}
