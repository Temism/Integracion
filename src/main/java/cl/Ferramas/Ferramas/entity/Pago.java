package cl.Ferramas.Ferramas.entity;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long pagoId;

    @Column( nullable = false)
    private BigDecimal monto;

    @Column( nullable = false)
    private String moneda = "CLP";


    private Date fechaPago;


    private String transaccionId;


    private String webpayToken;


    private String autorizacionCodigo;


    private Date fechaConfirmacion;


    private String notas;

    @ManyToOne
    @JoinColumn(name = "usuario_confirmacion_id")
    private Usuario usuarioConfirmacion;

    @ManyToOne
    @JoinColumn(name = "estado_pago_id", nullable = false)
    private EstadoPago estadoPago;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "metodo_id", nullable = false)
    private MetodoPago metodoPago;


    public Pago(Long id, BigDecimal pago, String moneda, Date fechaPago, String transaccionId, String webpayToken, String autorizacionCodigo, Date fechaConfirmacion, String notas, Usuario usuarioConfirmacion, EstadoPago estadoPago, Pedido pedido, MetodoPago metodoPago) {
        this.pagoId = id;
        monto = pago;
        this.moneda = moneda;
        this.fechaPago = fechaPago;
        this.transaccionId = transaccionId;
        this.webpayToken = webpayToken;
        this.autorizacionCodigo = autorizacionCodigo;
        this.fechaConfirmacion = fechaConfirmacion;
        this.notas = notas;
        this.usuarioConfirmacion = usuarioConfirmacion;
        this.estadoPago = estadoPago;
        this.pedido = pedido;
        this.metodoPago = metodoPago;
    }

    public Pago() {
    }

    public Long getPagoId() {
        return pagoId;
    }

    public void setPagoId(Long pagoId) {
        this.pagoId = pagoId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(String transaccionId) {
        this.transaccionId = transaccionId;
    }

    public String getWebpayToken() {
        return webpayToken;
    }

    public void setWebpayToken(String webpayToken) {
        this.webpayToken = webpayToken;
    }

    public String getAutorizacionCodigo() {
        return autorizacionCodigo;
    }

    public void setAutorizacionCodigo(String autorizacionCodigo) {
        this.autorizacionCodigo = autorizacionCodigo;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Usuario getUsuarioConfirmacion() {
        return usuarioConfirmacion;
    }

    public void setUsuarioConfirmacion(Usuario usuarioConfirmacion) {
        this.usuarioConfirmacion = usuarioConfirmacion;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    // MÉTODOS HELPER

    /**
     * Verifica si el pago está completado
     */
    public boolean estaCompletado() {
        return this.estadoPago != null && "COMPLETADO".equals(this.estadoPago.getNombre());
    }

    /**
     * Verifica si el pago está pendiente
     */
    public boolean estaPendiente() {
        return this.estadoPago != null && "PENDIENTE".equals(this.estadoPago.getNombre());
    }

    /**
     * Verifica si el pago está rechazado
     */
    public boolean estaRechazado() {
        return this.estadoPago != null && "RECHAZADO".equals(this.estadoPago.getNombre());
    }

    /**
     * Marca el pago como completado
     */
    public void confirmarPago(Usuario usuario, String codigoAutorizacion) {
        // Actualizar estado
        // Aquí se debería obtener el estado "COMPLETADO" de la base de datos
        EstadoPago estadoCompletado = new EstadoPago();
        estadoCompletado.setNombre("COMPLETADO");
        this.estadoPago = estadoCompletado;

        // Registrar confirmación
        this.usuarioConfirmacion = usuario;
        this.fechaConfirmacion = new Date();
        this.autorizacionCodigo = codigoAutorizacion;
    }

    /**
     * Marca el pago como rechazado
     */
    public void rechazarPago(String motivo) {
        // Actualizar estado
        // Aquí se debería obtener el estado "RECHAZADO" de la base de datos
        EstadoPago estadoRechazado = new EstadoPago();
        estadoRechazado.setNombre("RECHAZADO");
        this.estadoPago = estadoRechazado;

        this.notas = motivo;
    }

    /**
     * Obtiene la descripción del pago para comprobantes
     */
    public String getDescripcionComprobante() {
        return "Pago #" + this.pagoId +
                " - Pedido #" + this.pedido.getPedidoId() +
                " - " + this.metodoPago.getNombre() +
                " - " + this.monto + " " + this.moneda;
    }
}
