package cl.Ferramas.Ferramas.entity;

import jakarta.persistence.*;


import java.util.Date;



@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long inventarioId;
    @Column( nullable = false)
    private Long stockActual;
    @Column( nullable = false)
    private Long stockMinimo;

    private Long stockMaximo;
    private Date ultimaActualizacion;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    private UbicacionBodega ubicacion;

    public Inventario(Long id, Long stockActual, Integer stockMinimo, Long stockMaximo, Date ultimaActualizacion, Producto producto, Sucursal sucursal, UbicacionBodega ubicacion) {
        this.inventarioId = id;
        this.stockActual = stockActual;
        this.stockMinimo = Long.valueOf(stockMinimo);
        this.stockMaximo = stockMaximo;
        this.ultimaActualizacion = ultimaActualizacion;
        this.producto = producto;
        this.sucursal = sucursal;
        this.ubicacion = ubicacion;
    }


    public Inventario() {
    }

    public Long getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }

    public Integer getStockActual() {
        return Math.toIntExact(stockActual);
    }

    public void setStockActual(Long stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return Math.toIntExact(stockMinimo);
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = Long.valueOf(stockMinimo);
    }

    public Integer getStockMaximo() {
        return Math.toIntExact(stockMaximo);
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = Long.valueOf(stockMaximo);
    }

    public Date getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(Date ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
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

    public UbicacionBodega getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionBodega ubicacion) {
        this.ubicacion = ubicacion;
    }


    /**
     * Verifica si hay suficiente stock para la cantidad solicitada
     */
    public boolean tieneStockDisponible(Long cantidad) {
        return this.stockActual >= cantidad;
    }

    /**
     * Verifica si el stock está por debajo del mínimo
     */
    public boolean requiereReposicion() {
        return this.stockActual < this.stockMinimo;
    }

    /**
     * Calcula la cantidad necesaria para llegar al stock máximo
     */
    public int cantidadParaReposicionCompleta() {
        if (this.stockMaximo == null) {
            return Math.toIntExact(this.stockMinimo * 2 - this.stockActual);
        }
        return Math.toIntExact(this.stockMaximo - this.stockActual);
    }

    /**
     * Actualiza el stock y la fecha de actualización
     */
    public void actualizarStock(Long nuevoStock) {
        this.stockActual = nuevoStock;
        this.ultimaActualizacion = new Date();
    }

    /**
     * Incrementa el stock en la cantidad especificada
     */
    public void incrementarStock(Long cantidad) {
        this.stockActual += cantidad;
        this.ultimaActualizacion = new Date();
    }

    /**
     * disminuye el stock en la cantidad especificada si hay suficiente stock
     * @return true si se pudo decrementar, false si no hay suficiente stock
     */
    public boolean disminuirStock(Long cantidad) {
        if (tieneStockDisponible(cantidad)) {
            this.stockActual -= cantidad;
            this.ultimaActualizacion = new Date();
            return true;
        }
        return false;
    }
}
