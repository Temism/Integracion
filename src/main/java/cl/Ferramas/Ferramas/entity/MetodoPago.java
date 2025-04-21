package cl.Ferramas.Ferramas.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "metodo_pago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long metodoId;
    @Column( nullable = false)
    private String nombre;

    private String descripcion;

    private Boolean activo;


    @ManyToOne
    @JoinColumn(name = "tipo_pago_id", nullable = false)
    private TipoPago tipoPago;

    @OneToMany(mappedBy = "metodoPago", fetch = FetchType.LAZY)
    private List<Pago> pagos = new ArrayList<>();


    public MetodoPago(Long id, String nombre, String descripcion, Boolean activo, TipoPago tipoPago, List<Pago> pagos) {
        this.metodoId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
        this.tipoPago = tipoPago;
        this.pagos = pagos;
    }

    public MetodoPago() {
    }

    public Long getMetodoId() {
        return metodoId;
    }

    public void setMetodoId(Long metodoId) {
        this.metodoId = metodoId;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }




    public boolean getactivo() {
        return this.activo;
    }
}

