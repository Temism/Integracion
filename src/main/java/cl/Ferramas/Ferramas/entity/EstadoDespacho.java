package cl.Ferramas.Ferramas.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estados_despacho")

public class EstadoDespacho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estadoDespachoId;

    @Column(nullable = false, length = 50)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "estado")
    private List<Despacho> despachos = new ArrayList<>();


    public EstadoDespacho(Long id, String nombre, String descripcion, List<Despacho> despachos) {
        this.estadoDespachoId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.despachos = despachos;
    }

    public EstadoDespacho() {
    }

    public Long getEstadoDespachoId() {
        return estadoDespachoId;
    }

    public void setEstadoDespachoId(Long estadoDespachoId) {
        this.estadoDespachoId = estadoDespachoId;
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

    public List<Despacho> getDespachos() {
        return despachos;
    }

    public void setDespachos(List<Despacho> despachos) {
        this.despachos = despachos;
    }
}