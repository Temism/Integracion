package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PedidoRep extends JpaRepository<Pedido,Long> {

}
