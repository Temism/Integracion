package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventarioRep extends JpaRepository<Inventario,Long> {



}
