package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.TipoEntrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoEntregaRep extends JpaRepository<TipoEntrega,Long> {

}
