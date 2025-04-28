package cl.Ferramas.Ferramas.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "metodo_pago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metodoId;

    @Column(nullable = false, length = 50)
    private String nombre;

    private String descripcion;

    private Boolean activo = true;

    @OneToMany(mappedBy = "metodoPago")
    private List<Pago> pagos = new ArrayList<>();


    public MetodoPago(Long metodoId, String nombre, String descripcion, Boolean activo, List<Pago> pagos) {
        this.metodoId = metodoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
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

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}

