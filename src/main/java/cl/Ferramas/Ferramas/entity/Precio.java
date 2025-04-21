package cl.Ferramas.Ferramas.entity;

import jakarta.persistence.*;


import java.util.Date;


@Entity
@Table(name = "precio")
public class Precio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long precioId;
    @Column( nullable = false)
    private Double valor;

    private String moneda = "CLP";
    @Column( nullable = false)
    private Date fechainicio;

    private Date fechafin;


    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;


    public Precio(Long id, Double valor, String moneda, Date fechaInicio, Date fechaFin, Producto producto) {
        this.precioId = id;
        this.valor = valor;
        this.moneda = moneda;
        fechainicio = fechaInicio;
        fechafin = fechaFin;
        this.producto = producto;
    }

    public Precio() {
    }

    public Long getPrecioId() {
        return precioId;
    }

    public void setPrecioId(Long precioId) {
        this.precioId = precioId;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
