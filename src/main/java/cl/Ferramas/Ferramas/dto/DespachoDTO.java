package cl.Ferramas.Ferramas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespachoDTO {

    private Long Id;
    private LocalDate fechaDespacho;
    private LocalDate fechaEntregaEstimada;
    private LocalDate fechaEntregaReal;
    private String numeroGuia;
    private String Transportista;
    private Long estadiId;
    private Long pedidoId;
    private BigDecimal costoDespacho;
    private String estado;


    public DespachoDTO(Long id, LocalDate fechaDespacho,String estado, LocalDate fechaEntregaEstimada, LocalDate fechaEntregaReal, String numeroGuia, String transportista, Long estadiId, Long pedidoId, BigDecimal costoDespacho) {
        Id = id;
        this.fechaDespacho = fechaDespacho;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.fechaEntregaReal = fechaEntregaReal;
        this.numeroGuia = numeroGuia;
        Transportista = transportista;
        this.estadiId = estadiId;
        this.pedidoId = pedidoId;
        this.costoDespacho = costoDespacho;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DespachoDTO() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(LocalDate fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public LocalDate getFechaEntregaReal() {
        return fechaEntregaReal;
    }

    public void setFechaEntregaReal(LocalDate fechaEntregaReal) {
        this.fechaEntregaReal = fechaEntregaReal;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public String getTransportista() {
        return Transportista;
    }

    public void setTransportista(String transportista) {
        Transportista = transportista;
    }

    public Long getEstadiId() {
        return estadiId;
    }

    public void setEstadiId(Long estadiId) {
        this.estadiId = estadiId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public BigDecimal getCostoDespacho() {
        return costoDespacho;
    }

    public void setCostoDespacho(BigDecimal costoDespacho) {
        this.costoDespacho = costoDespacho;
    }
}




