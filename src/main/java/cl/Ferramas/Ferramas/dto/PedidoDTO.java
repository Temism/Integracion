package cl.Ferramas.Ferramas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoDTO {

    private Long id;
    private String codigo;
    private LocalDate fechaPedido;
    private String direccionEntrega;
    private BigDecimal Subtotal;
    private BigDecimal Total;
    private BigDecimal descuento;
    private BigDecimal iva;
    private String nota;
    private String estado;
    private String tipoentrega;
    private Long estadoId;
    private Long clienteId;
    private Long vendedorId;
    private Long sucursalId;
    private Long tipoEntregaId;


    public PedidoDTO(Long id, String codigo,String estado,String tipoentrega, LocalDate fechaPedido, String direccionEntrega, BigDecimal subtotal, BigDecimal total, BigDecimal descuento, BigDecimal iva, String nota, Long estadoId, Long clienteId, Long vendedorId, Long sucursalId, Long tipoEntregaId) {
        this.estado = estado;
        this.id = id;
        this.tipoentrega= tipoentrega;
        this.codigo = codigo;
        this.fechaPedido = fechaPedido;
        this.direccionEntrega = direccionEntrega;
        Subtotal = subtotal;
        Total = total;
        this.descuento = descuento;
        this.iva = iva;
        this.nota = nota;
        this.estadoId = estadoId;
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.sucursalId = sucursalId;
        this.tipoEntregaId = tipoEntregaId;
    }

    public String getTipoentrega() {
        return tipoentrega;
    }

    public void setTipoentrega(String tipoentrega) {
        this.tipoentrega = tipoentrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PedidoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public BigDecimal getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        Subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public Long getTipoEntregaId() {
        return tipoEntregaId;
    }

    public void setTipoEntregaId(Long tipoEntregaId) {
        this.tipoEntregaId = tipoEntregaId;
    }
}
