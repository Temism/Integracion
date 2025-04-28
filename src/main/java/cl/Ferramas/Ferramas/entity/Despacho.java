package cl.Ferramas.Ferramas.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "despachos")

public class Despacho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long despachoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name = "fecha_despacho")
    private LocalDateTime fechaDespacho;

    @Column(name = "fecha_entrega_estimada")
    private LocalDateTime fechaEntregaEstimada;

    @Column(name = "fecha_entrega_real")
    private LocalDateTime fechaEntregaReal;

    @Column(length = 100)
    private String transportista;

    @Column(name = "numero_guia", length = 50)
    private String numeroGuia;

    @Column(name = "costo_despacho", precision = 12, scale = 2)
    private BigDecimal costoDespacho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoDespacho estado;


    public Despacho(Long id, Pedido pedido, LocalDateTime fechaDespacho, LocalDateTime fechaEntregaEstimada, LocalDateTime fechaEntregaReal, String transportista, String numeroGuia, BigDecimal costoDespacho, EstadoDespacho estado) {
        this.despachoId = id;
        this.pedido = pedido;
        this.fechaDespacho = fechaDespacho;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.fechaEntregaReal = fechaEntregaReal;
        this.transportista = transportista;
        this.numeroGuia = numeroGuia;
        this.costoDespacho = costoDespacho;
        this.estado = estado;
    }


    public Despacho() {
    }

    public Long getDespachoId() {
        return despachoId;
    }

    public void setDespachoId(Long despachoId) {
        this.despachoId = despachoId;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public LocalDateTime getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(LocalDateTime fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public LocalDateTime getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDateTime fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public LocalDateTime getFechaEntregaReal() {
        return fechaEntregaReal;
    }

    public void setFechaEntregaReal(LocalDateTime fechaEntregaReal) {
        this.fechaEntregaReal = fechaEntregaReal;
    }

    public String getTransportista() {
        return transportista;
    }

    public void setTransportista(String transportista) {
        this.transportista = transportista;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public BigDecimal getCostoDespacho() {
        return costoDespacho;
    }

    public void setCostoDespacho(BigDecimal costoDespacho) {
        this.costoDespacho = costoDespacho;
    }

    public EstadoDespacho getEstado() {
        return estado;
    }

    public void setEstado(EstadoDespacho estado) {
        this.estado = estado;
    }
}
