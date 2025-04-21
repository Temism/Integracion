package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "referencia_movimiento")
public class ReferenciaMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referenciaId;

    private Date fechaCreacion = new Date();

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "tipo_referencia_id", nullable = false)
    private TipoReferencia tipoReferencia;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @OneToMany(mappedBy = "referencia", fetch = FetchType.LAZY)
    private List<MovimientoInventario> movimientos = new ArrayList<>();


    public ReferenciaMovimiento(Long id, Date fechaCreacion, String descripcion, TipoReferencia tipoReferencia, Pedido pedido, List<MovimientoInventario> movimientos) {
        this.referenciaId = id;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.tipoReferencia = tipoReferencia;
        this.pedido = pedido;
        this.movimientos = movimientos;
    }

    public ReferenciaMovimiento() {
    }

    public Long getReferenciaId() {
        return referenciaId;
    }

    public void setReferenciaId(Long referenciaId) {
        this.referenciaId = referenciaId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoReferencia getTipoReferencia() {
        return tipoReferencia;
    }

    public void setTipoReferencia(TipoReferencia tipoReferencia) {
        this.tipoReferencia = tipoReferencia;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<MovimientoInventario> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoInventario> movimientos) {
        this.movimientos = movimientos;
    }

    public void setTipoReferencia(MovimientoInventario movimientoInventario) {

    }
}

