package cl.Ferramas.Ferramas.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;


@Entity
@Table(name = "movimiento_inventario")
@Data
public class MovimientoInventario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimientoId;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_movimiento")
    private Date fechaMovimiento = new Date();

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_destino_id")
    private Sucursal sucursalDestino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referencia_id")
    private ReferenciaMovimiento referencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_movimiento_id", nullable = false)
    private TipoMovimiento tipoMovimiento;

    public MovimientoInventario(Long movimientoId, Integer cantidad, Date fechaMovimiento, String descripcion, Sucursal sucursalDestino, ReferenciaMovimiento referencia, Usuario usuario, Producto producto, Sucursal sucursal, TipoMovimiento tipoMovimiento) {
        this.movimientoId = movimientoId;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.descripcion = descripcion;
        this.sucursalDestino = sucursalDestino;
        this.referencia = referencia;
        this.usuario = usuario;
        this.producto = producto;
        this.sucursal = sucursal;
        this.tipoMovimiento = tipoMovimiento;
    }

    public MovimientoInventario() {
    }

    public Long getMovimientoId() {
        return movimientoId;
    }

    public void setMovimientoId(Long movimientoId) {
        this.movimientoId = movimientoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sucursal getSucursalDestino() {
        return sucursalDestino;
    }

    public void setSucursalDestino(Sucursal sucursalDestino) {
        this.sucursalDestino = sucursalDestino;
    }

    public ReferenciaMovimiento getReferencia() {
        return referencia;
    }

    public void setReferencia(ReferenciaMovimiento referencia) {
        this.referencia = referencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

// MÉTODOS HELPER


    public boolean esEntrada() {
        return tipoMovimiento != null && "ENTRADA".equalsIgnoreCase(tipoMovimiento.getNombre());
    }


    public boolean esSalida() {
        return tipoMovimiento != null && "SALIDA".equalsIgnoreCase(tipoMovimiento.getNombre());
    }


    public boolean esTraspaso() {
        return tipoMovimiento != null && "TRASPASO".equalsIgnoreCase(tipoMovimiento.getNombre());
    }


    public boolean esAjuste() {
        return tipoMovimiento != null && "AJUSTE".equalsIgnoreCase(tipoMovimiento.getNombre());
    }


    public void validarMovimiento() {
        if (cantidad == null || cantidad <= 0) {
            throw new IllegalStateException("La cantidad debe ser un valor positivo");
        }
        if (producto == null) {
            throw new IllegalStateException("El producto es requerido");
        }
        if (sucursal == null) {
            throw new IllegalStateException("La sucursal de origen es requerida");
        }
        if (usuario == null) {
            throw new IllegalStateException("El usuario es requerido");
        }
        if (tipoMovimiento == null) {
            throw new IllegalStateException("El tipo de movimiento es requerido");
        }
        if (esTraspaso() && sucursalDestino == null) {
            throw new IllegalStateException("La sucursal destino es requerida para traspasos");
        }
    }


    public void aplicarMovimiento(Inventario inventarioOrigen, Inventario inventarioDestino) {
        validarMovimiento();

        if (esEntrada()) {
            inventarioOrigen.incrementarStock(Long.valueOf(cantidad));
        } else if (esSalida()) {
            inventarioOrigen.disminuirStock(Long.valueOf(cantidad));
        } else if (esTraspaso()) {
            if (inventarioDestino == null) {
                throw new IllegalStateException("En un traspaso debe existir inventario destino");
            }
            if (sucursalDestino == null) {
                throw new IllegalStateException("En un traspaso debe especificarse la sucursal destino");
            }

            inventarioOrigen.disminuirStock(Long.valueOf(cantidad));
            inventarioDestino.incrementarStock(Long.valueOf(cantidad));
        } else if (esAjuste()) {
            inventarioOrigen.actualizarStock(Long.valueOf(inventarioOrigen.getStockActual() + cantidad));
        }
    }


    public MovimientoInventario crearMovimientoInverso(Usuario usuarioAnulacion, String motivoAnulacion) {
        if (usuarioAnulacion == null) {
            throw new IllegalArgumentException("El usuario de anulación es requerido");
        }
        if (motivoAnulacion == null || motivoAnulacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo de anulación es requerido");
        }

        MovimientoInventario inverso = new MovimientoInventario();
        inverso.setProducto(this.producto);
        inverso.setSucursal(this.sucursal);
        inverso.setUsuario(usuarioAnulacion);
        inverso.setFechaMovimiento(new Date());
        inverso.setReferencia(this.referencia);
        inverso.setDescripcion("Anulación de movimiento #" + this.movimientoId + ": " + motivoAnulacion);

        if (esEntrada()) {
            // Si era entrada, ahora es salida
            inverso.setTipoMovimiento(obtenerTipoMovimientoInverso("SALIDA"));
            inverso.setCantidad(this.cantidad);
        } else if (esSalida()) {
            // Si era salida, ahora es entrada
            inverso.setTipoMovimiento(obtenerTipoMovimientoInverso("ENTRADA"));
            inverso.setCantidad(this.cantidad);
        } else if (esTraspaso()) {
            // Si era traspaso, invertimos origen y destino
            inverso.setTipoMovimiento(this.tipoMovimiento);
            inverso.setSucursal(this.sucursalDestino);
            inverso.setSucursalDestino(this.sucursal);
            inverso.setCantidad(this.cantidad);
        } else if (esAjuste()) {
            // Si era ajuste, invertimos el signo de la cantidad
            inverso.setTipoMovimiento(this.tipoMovimiento);
            inverso.setCantidad(-this.cantidad);
        }

        return inverso;
    }


    public String obtenerResumen() {
        return String.format("Movimiento #%d - %s: %d unidades de %s en %s",
                movimientoId,
                tipoMovimiento != null ? tipoMovimiento.getNombre() : "SIN TIPO",
                cantidad,
                producto != null ? producto.getNombre() : "SIN PRODUCTO",
                sucursal != null ? sucursal.getNombre() : "SIN SUCURSAL");
    }


    public boolean puedeAnularse() {

        Date fechaLimite = new Date(System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000)); // 7 días atrás
        return fechaMovimiento.after(fechaLimite);
    }


    private TipoMovimiento obtenerTipoMovimientoInverso(String nombreTipoInverso) {
        TipoMovimiento tipo = new TipoMovimiento();
        tipo.setNombre(nombreTipoInverso);

        return tipo;
    }
}
