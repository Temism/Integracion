package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


public interface InventarioRep extends JpaRepository<Inventario,Long> {


    @Query("SELECT i FROM Inventario i WHERE i.sucursal.id = :sucursalId")
    List<Inventario> inventarioporsucursal(@Param("sucursalId") Long sucursalId);


}
