package cl.Ferramas.Ferramas.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tipo_referencia")
public class TipoReferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipoReferenciaId;

    @Column( nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "tipoReferencia",fetch = FetchType.LAZY)
    private List<ReferenciaMovimiento> referencias = new ArrayList<>();


    public TipoReferencia(Long id, String nombre, String descripcion, List<ReferenciaMovimiento> referencias) {
        this.tipoReferenciaId = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.referencias = referencias;
    }


    public TipoReferencia() {
    }

    public Long getTipoReferenciaId() {
        return tipoReferenciaId;
    }

    public void setTipoReferenciaId(Long tipoReferenciaId) {
        this.tipoReferenciaId = tipoReferenciaId;
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

    public List<ReferenciaMovimiento> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<ReferenciaMovimiento> referencias) {
        this.referencias = referencias;
    }
}
