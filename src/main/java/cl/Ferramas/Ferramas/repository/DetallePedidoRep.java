package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoRep extends JpaRepository<DetallePedido,Long> {


}
