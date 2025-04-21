package cl.Ferramas.Ferramas.entity;

import jakarta.persistence.*;


import java.util.Date;



@Entity
@Table(name = "historial_estado_pedido")
public class HistorialEstadoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long historialId;

    private Date fechaCambio;

    private String comentario;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "estado_pedido_id", nullable = false)
    private EstadoPedido estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    public HistorialEstadoPedido(Long id, Date fechaCambio, String comentario, Pedido pedido, EstadoPedido estado, Usuario usuario) {
        this.historialId = id;
        this.fechaCambio = fechaCambio;
        this.comentario = comentario;
        this.pedido = pedido;
        this.estado = estado;
        this.usuario = usuario;
    }

    public HistorialEstadoPedido() {
    }

    public Long getHistorialId() {
        return historialId;
    }

    public void setHistorialId(Long historialId) {
        this.historialId = historialId;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
