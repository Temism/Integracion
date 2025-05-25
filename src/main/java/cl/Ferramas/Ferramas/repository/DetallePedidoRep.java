package cl.Ferramas.Ferramas.repository;

import cl.Ferramas.Ferramas.entity.DetallePedido;
import cl.Ferramas.Ferramas.projection.ProductoCantidadProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetallePedidoRep extends JpaRepository<DetallePedido, Long> {

    @Query("SELECT p.nombre AS nombreProducto, SUM(dp.cantidad) AS totalCantidad " +
           "FROM DetallePedido dp JOIN dp.producto p " +
           "GROUP BY p.nombre " +
           "ORDER BY totalCantidad DESC")
    List<ProductoCantidadProjection> obtenerProductosMasVendidos();
}
