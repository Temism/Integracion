package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Producto;
import cl.Ferramas.Ferramas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRep extends JpaRepository<Usuario,Long>  {




}
