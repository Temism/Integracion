package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tipo_pago")
public class TipoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoPagoId;
    @Column( nullable = false)
    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "tipoPago",fetch = FetchType.LAZY)
    private List<MetodoPago> metodosPago = new ArrayList<>();


    public TipoPago(Long id, String nombre, String descripcion, List<MetodoPago> metodosPago) {
        this.tipoPagoId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.metodosPago = metodosPago;
    }


    public TipoPago() {
    }


    public Long getTipoPagoId() {
        return tipoPagoId;
    }

    public void setTipoPagoId(Long tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<MetodoPago> getMetodosPago() {
        return metodosPago;
    }

    public void setMetodosPago(List<MetodoPago> metodosPago) {
        this.metodosPago = metodosPago;
    }
}
