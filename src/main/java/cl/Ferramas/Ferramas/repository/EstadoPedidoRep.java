package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstadoPedidoRep extends JpaRepository<EstadoPedido, Long> {


}