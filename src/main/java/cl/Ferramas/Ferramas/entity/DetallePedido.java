package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long detallePedidoId;
    @Column( nullable = false)
    private Long cantidad;
    @Column(nullable = false)
    private BigDecimal precioUnitario;

    private BigDecimal descuento;
    @Column( nullable = false)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;


    public DetallePedido(Long id, Long cantidad, BigDecimal precioUnitario, BigDecimal descuento, BigDecimal subtotal, Pedido pedido, Producto producto) {
        this.detallePedidoId = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        this.subtotal = subtotal;
        this.pedido = pedido;
        this.producto = producto;
    }

    public DetallePedido() {
    }

    public Long getDetallePedidoId() {
        return detallePedidoId;
    }

    public void setDetallePedidoId(Long detallePedidoId) {
        this.detallePedidoId = detallePedidoId;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}

