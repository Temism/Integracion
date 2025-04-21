package cl.Ferramas.Ferramas.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tipo_movimiento")
public class TipoMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoMovimientoId;
    @Column(nullable = false, unique = true)
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "tipoMovimiento",fetch = FetchType.LAZY)
    private List<MovimientoInventario> movimientos = new ArrayList<>();


    public TipoMovimiento(Long id, String nombre, String descripcion, List<MovimientoInventario> movimientos) {
        this.tipoMovimientoId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.movimientos = movimientos;
    }


    public TipoMovimiento() {
    }

    public Long getTipoMovimientoId() {
        return tipoMovimientoId;
    }

    public void setTipoMovimientoId(Long tipoMovimientoId) {
        this.tipoMovimientoId = tipoMovimientoId;
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
