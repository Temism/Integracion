package cl.Ferramas.Ferramas.entity;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long pedidoId;

    private Date fechaPedido;
    @Column( nullable = false)
    private String tipoentrega;
    @Column( nullable = false)
    private Date fechaEstimadaEntrega;
    @Column( nullable = false)
    private BigDecimal subtotal;

    private BigDecimal descuento;
    @Column( nullable = false)
    private BigDecimal impuestoIva;
    @Column( nullable = false)
    private BigDecimal total;

    private String comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "estado_pedido_id", nullable = false)
    private EstadoPedido estado;

    @ManyToOne
    @JoinColumn(name = "tipo_entrega_id", nullable = false)
    private TipoEntrega tipoEntrega;

    @ManyToOne
    @JoinColumn(name = "direccion_entrega_id")
    private Direccion direccionEntrega;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(name = "bodeguero_id")
    private Usuario bodeguero;

    @ManyToOne
    @JoinColumn(name = "contador_id")
    private Usuario contador;


    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles = new ArrayList<>();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<HistorialEstadoPedido> historialEstados = new ArrayList<>();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Pago> pagos = new ArrayList<>();

    @OneToMany(mappedBy = "pedido")
    private List<ReferenciaMovimiento> referencias = new ArrayList<>();


    public Pedido(Long id, Date fechaPedido, String tipoEntrega, Date fechaEstimadaEntrega, BigDecimal subtotal, BigDecimal descuento, BigDecimal impuestoIva, BigDecimal total, String comentario, Usuario usuario, Sucursal sucursal, EstadoPedido estado, cl.Ferramas.Ferramas.entity.TipoEntrega tipoEntrega1, Direccion direccionEntrega, Usuario vendedor, Usuario bodeguero, Usuario contador, List<DetallePedido> detalles, List<HistorialEstadoPedido> historialEstados, List<Pago> pagos, List<ReferenciaMovimiento> referencias) {
        this.pedidoId = id;
        this.fechaPedido = fechaPedido;
        tipoentrega = tipoEntrega;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.impuestoIva = impuestoIva;
        this.total = total;
        this.comentario = comentario;
        this.usuario = usuario;
        this.sucursal = sucursal;
        this.estado = estado;
        this.tipoEntrega = tipoEntrega1;
        this.direccionEntrega = direccionEntrega;
        this.vendedor = vendedor;
        this.bodeguero = bodeguero;
        this.contador = contador;
        this.detalles = detalles;
        this.historialEstados = historialEstados;
        this.pagos = pagos;
        this.referencias = referencias;
    }

    public Pedido() {
    }


    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getTipoentrega() {
        return tipoentrega;
    }

    public void setTipoentrega(cl.Ferramas.Ferramas.entity.TipoEntrega tipoentrega) {
        this.tipoEntrega = tipoentrega;
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Usuario getBodeguero() {
        return bodeguero;
    }

    public void setBodeguero(Usuario bodeguero) {
        this.bodeguero = bodeguero;
    }

    public Usuario getContador() {
        return contador;
    }

    public void setContador(Usuario contador) {
        this.contador = contador;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public List<HistorialEstadoPedido> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(List<HistorialEstadoPedido> historialEstados) {
        this.historialEstados = historialEstados;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public List<ReferenciaMovimiento> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<ReferenciaMovimiento> referencias) {
        this.referencias = referencias;
    }

    public void setTipoEntrega(String tipoEntrega) {
        tipoentrega = tipoEntrega;
    }

    public Date getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(Date fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getImpuestoIva() {
        return impuestoIva;
    }

    public void setImpuestoIva(BigDecimal impuestoIva) {
        this.impuestoIva = impuestoIva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }


    /**
     * Agrega un nuevo detalle al pedido y recalcula los totales
     */
    public void agregarDetalle(Producto producto, Long cantidad, Double precioUnitario) {
        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(this);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(BigDecimal.valueOf(precioUnitario));

        // Calcular subtotal del detalle
        Double subtotalDetalle = precioUnitario * cantidad;
        detalle.setSubtotal(BigDecimal.valueOf(subtotalDetalle));

        // Agregar el detalle a la lista
        this.detalles.add(detalle);

        // Recalcular totales del pedido
        recalcularTotales();
    }

    /**
     * Recalcula subtotal, impuesto y total del pedido
     */
    public void recalcularTotales() {
        // Calcular subtotal sumando todos los detalles
        this.subtotal = BigDecimal.ZERO;
        for (DetallePedido detalle : this.detalles) {
            this.subtotal = this.subtotal.add(detalle.getSubtotal());
        }

        // Aplicar descuento si existe
        if (this.descuento == null) {
            this.descuento = BigDecimal.ZERO;
        }

        // Calcular monto después de descuento
        BigDecimal montoTrasDescuento = this.subtotal.subtract(this.descuento);

        // Calcular impuesto (19% en Chile por ejemplo)
        BigDecimal tasaImpuesto = new BigDecimal("0.19");
        this.impuestoIva = montoTrasDescuento.multiply(tasaImpuesto).setScale(2, RoundingMode.HALF_UP);

        // Calcular total
        this.total = montoTrasDescuento.add(this.impuestoIva);
    }

    /**
     * Verifica si el pedido puede pasar al estado especificado
     */
    public boolean puedeTransicionarA(EstadoPedido nuevoEstado) {
        // Lógica para validar transiciones permitidas
        // Por ejemplo: No se puede pasar de "Entregado" a "En Preparación"

        // Ejemplo simple:
        if (this.estado.getNombre().equals("CANCELADO")) {
            return false; // No se puede cambiar un pedido cancelado
        }

        if (this.estado.getNombre().equals("ENTREGADO") &&
                !nuevoEstado.getNombre().equals("FINALIZADO") &&
                !nuevoEstado.getNombre().equals("CANCELADO")) {
            return false;
        }

        return true;
    }

    /**
     * Cambia el estado del pedido y registra el cambio en el historial
     */
    public HistorialEstadoPedido cambiarEstado(EstadoPedido nuevoEstado, Usuario usuario, String comentario) {
        if (!puedeTransicionarA(nuevoEstado)) {
            throw new IllegalStateException("No se puede cambiar al estado solicitado");
        }

        this.estado = nuevoEstado;

        // Crear registro histórico
        HistorialEstadoPedido historial = new HistorialEstadoPedido();
        historial.setPedido(this);
        historial.setEstado(nuevoEstado);
        historial.setUsuario(usuario);
        historial.setFechaCambio(new Date());
        historial.setComentario(comentario);

        return historial;
    }

    /**
     * Verifica si el pedido tiene todos sus pagos completados
     */
    public boolean estaPagado(List<Pago> pagos) {
        // Sumar todos los pagos asociados a este pedido
        BigDecimal totalPagado = BigDecimal.ZERO;
        for (Pago pago : pagos) {
            if (pago.getPedido().getPedidoId().equals(this.pedidoId) &&
                    pago.getEstadoPago().getNombre().equalsIgnoreCase("COMPLETADO")) {
                totalPagado = totalPagado.add(pago.getMonto());
            }
        }

        // Comparar con el total del pedido con margen de error (epsilon)
        BigDecimal epsilon = new BigDecimal("0.01");
        BigDecimal diferencia = this.total.subtract(totalPagado).abs();

        return diferencia.compareTo(epsilon) <= 0;
    }
    public void setEstadoPedido(EstadoPedido estadoInicial) {
        
    }

    public void setFechaEntregaEstimada(Date date) {

    }
}
