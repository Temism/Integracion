package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Categoria;
import cl.Ferramas.Ferramas.entity.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductoRep extends JpaRepository<Producto, Long> {


}
