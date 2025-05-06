package cl.Ferramas.Ferramas.dto;

import java.math.BigDecimal;

public class DetallePedidoDTO {

    private Long id;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuentounitario;
    private BigDecimal total;
    private Long pedidoId;
    private Long productoId;


    public DetallePedidoDTO(Long id, Integer cantidad, BigDecimal precioUnitario, BigDecimal descuentounitario, BigDecimal total, Long pedidoId, Long productoId) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuentounitario = descuentounitario;
        this.total = total;
        this.pedidoId = pedidoId;
        this.productoId = productoId;
    }

    public DetallePedidoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getDescuentounitario() {
        return descuentounitario;
    }

    public void setDescuentounitario(BigDecimal descuentounitario) {
        this.descuentounitario = descuentounitario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
}



