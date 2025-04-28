package cl.Ferramas.Ferramas.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tipo_movimiento")
public class TipoMovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoMovimientoInventarioId;

    @Column(nullable = false, length = 50)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "tipoMovimiento")
    private List<MovimientoInventario> movimientos = new ArrayList<>();


    public TipoMovimientoInventario(Long tipoMovimientoInventarioId, String nombre, String descripcion, List<MovimientoInventario> movimientos) {
        this.tipoMovimientoInventarioId = tipoMovimientoInventarioId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.movimientos = movimientos;
    }


    public TipoMovimientoInventario() {
    }

    public Long getTipoMovimientoInventarioId() {
        return tipoMovimientoInventarioId;
    }

    public void setTipoMovimientoInventarioId(Long tipoMovimientoInventarioId) {
        this.tipoMovimientoInventarioId = tipoMovimientoInventarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<MovimientoInventario> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoInventario> movimientos) {
        this.movimientos = movimientos;
    }
}
