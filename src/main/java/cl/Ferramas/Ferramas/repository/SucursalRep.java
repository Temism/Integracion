package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Sucursal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SucursalRep extends JpaRepository<Sucursal,Long> {

}
